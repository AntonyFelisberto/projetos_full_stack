package com.projetooficina.sistemaoficinaback.services;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.projetooficina.sistemaoficinaback.exception.EmailSendException;
import com.projetooficina.sistemaoficinaback.model.Email;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class MessageService {

    private JavaMailSender javaMailSender;
    private AmazonSNSClient amazonSNSClient;

    public void cadastrarNumeroAws(String numero){
        SubscribeRequest subscribe = new SubscribeRequest("arn:aws:sns:us-east-1:333947437539:sms-topic","sms",numero);
        amazonSNSClient.subscribe(subscribe);
    }

    public void sendSms(String mensagem, String numero){

        //CONTINUAR CONFIGURANDO PARA ENVIAR E-MAILS
        Map<String, MessageAttributeValue> smsAttributes = new HashMap<>();
        smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue()
                .withStringValue("mySenderID")
                .withDataType("String"));
        smsAttributes.put("AWS.SNS.SMS.MaxPrice", new MessageAttributeValue()
                .withStringValue("0.50")
                .withDataType("Number"));
        smsAttributes.put("AWS.SNS.SMS.SMSType",new MessageAttributeValue()
                .withStringValue("Transactional")
                .withDataType("String"));

        //ENVIANDO O SMS
        PublishRequest publishRequest = new PublishRequest();
        publishRequest.setMessage(mensagem);
        publishRequest.setMessageAttributes(smsAttributes);
        publishRequest.setSubject("AVISO DA APLICAÇÃO SISTEMA V8");
        publishRequest.setPhoneNumber(numero);
        /*PARA O SMS SER ENVIADO É NECESSARIO O NUMERO ESTAR CADASTRADO EM

            https://us-east-1.console.aws.amazon.com/sns/v3/home?region=us-east-1#/mobile/text-messaging

          NA PARTE DE Números de telefone de destino da sandbox
        */
        amazonSNSClient.publish(publishRequest);
    }

    public void sendMail(Email email){
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeBodyPart arquivoPdf = new MimeBodyPart();
            Multipart corpoDoEmail = new MimeMultipart();
            BodyPart html = new MimeBodyPart();

            mimeMessage(email, message, arquivoPdf, corpoDoEmail);

            html.setContent(email.getText() , "text/html");
            corpoDoEmail.addBodyPart(html);

            message.setContent(corpoDoEmail);

            javaMailSender.send(message);
        }catch (Exception erro) {
            throw new EmailSendException("Erro ao enviar email");
        }
    }

    public void sendSomentePdfEmail(Email email){
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeBodyPart arquivoPdf = new MimeBodyPart();
            Multipart corpoDoEmail = new MimeMultipart();

            mimeMessage(email, message, arquivoPdf, corpoDoEmail);

            message.setContent(corpoDoEmail);

            javaMailSender.send(message);
        }catch (Exception erro) {
            throw new EmailSendException("Erro ao enviar email");
        }
    }

    private void mimeMessage(Email email, MimeMessage message, MimeBodyPart arquivoPdf, Multipart corpoDoEmail) throws MessagingException {
        MimeMessageHelper messageHelper = new MimeMessageHelper(message,MimeMessageHelper.MULTIPART_MODE_MIXED, "UTF-8");
        messageHelper.setFrom(email.getFrom());
        messageHelper.setTo(email.getTo());
        messageHelper.setSubject(email.getSubject());
        message.setSentDate(new java.util.Date());

        InternetHeaders headers = new InternetHeaders();
        headers.addHeader("Content-Type", "application/pdf");

        arquivoPdf.setDataHandler(new DataHandler(new ByteArrayDataSource(email.getPdfByte(), "aplication/pdf")));
        arquivoPdf.setFileName("Orcamento.pdf");
        corpoDoEmail.addBodyPart(arquivoPdf);
    }

}
