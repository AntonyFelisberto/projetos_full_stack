package com.projetooficina.sistemaoficinaback.services;

import com.projetooficina.sistemaoficinaback.dto.DadosOrcamentoDTO;
import com.projetooficina.sistemaoficinaback.dto.EmailHtml;
import com.projetooficina.sistemaoficinaback.exception.DataFormation;
import com.projetooficina.sistemaoficinaback.exception.EmailSendException;
import com.projetooficina.sistemaoficinaback.exception.ObjectNotFoundException;
import com.projetooficina.sistemaoficinaback.model.Email;
import com.projetooficina.sistemaoficinaback.model.Orcamento;
import com.projetooficina.sistemaoficinaback.repository.OrcamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class OrcamentoServices {

    private final OrcamentoRepository orcamentoRepository;
    private final HtmlToPdfApplication htmlToPdfApplication;
    private MessageService messageService;

    public void enviarPorId(Long idOrcamento){
        try {
            Orcamento orcamento = encontrarOrcamentoPorId(idOrcamento);
            messageService.sendSomentePdfEmail(
                    new Email(
                            "oficinav8cambe@hotmail.com",
                            orcamento.getCliente().getEmail(),
                            "Orcamento Sistema V8",
                            null,
                            orcamento.getPdf()
                    )
            );
        }catch (Exception erro){
            throw new EmailSendException("Erro ao reenviar orçamento");
        }
    }

    public void adicionarOrcamento(Long idUsuario, DadosOrcamentoDTO orcamentoDTO){
        try {
            EmailHtml emailHtml = new EmailHtml();
            Orcamento orcamento = new Orcamento();
            orcamento.setIdOrcamento(null);
            orcamento.setData(new Date());
            orcamento.setCliente(orcamentoDTO.getCliente());
            orcamento.setPdf(htmlToPdfApplication.createPdf(idUsuario,orcamentoDTO,emailHtml));
            orcamentoRepository.save(orcamento);
            messageService.sendMail(
                    new Email(
                            "oficinav8cambe@hotmail.com",
                            orcamentoDTO.getCliente().getEmail(),
                            "Orcamento Sistema V8",
                            emailHtml.getHtml(),
                            orcamento.getPdf()
                    )
            );
        }catch (Exception erro){
            throw new DataFormation("Erro ao fazer inserção de dados do orçamento"+erro.getMessage());
        }
    }

    public List<Orcamento> encontrarTodosOsOrcamento(){
        return orcamentoRepository.findAll();
    }

    public void deleteOrcamento(Long id){
        orcamentoRepository.deleteById(id);
    }

    public Orcamento encontrarOrcamentoPorId(Long id){
        return orcamentoRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Usuario do id "+id+"não foi encontrado"));
    }

    public Long pegarIdMaximo() {
        Long cronogramas = orcamentoRepository.findMaxId();
        if(cronogramas != null){
            return cronogramas+1;
        }else {
            return 1L;
        }
    }

}

