package com.antony.helpdesk.services;

import com.antony.helpdesk.enums.Priority;
import com.antony.helpdesk.enums.Profile;
import com.antony.helpdesk.enums.Status;
import com.antony.helpdesk.model.Call;
import com.antony.helpdesk.model.Client;
import com.antony.helpdesk.model.Technical;
import com.antony.helpdesk.repositories.CallRepository;
import com.antony.helpdesk.repositories.ClientRepository;
import com.antony.helpdesk.repositories.PersonRepository;
import com.antony.helpdesk.repositories.TechnicalRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class DataBaseServices {

    private final CallRepository callRepository;

    private final TechnicalRepository technicalRepository;

    private final PersonRepository personRepository;

    private final ClientRepository clientRepository;

    private BCryptPasswordEncoder encoder;

    public void intanciarData(){
        Technical tecnico = new Technical(null,"antony","55557556605","antony11@email.com",encoder.encode("1123"));
        tecnico.addPerfil(Profile.CLIENTE);
        Technical tecnicoUm = new Technical(null,"antonyAA","79846342381","antonydd@email.com",encoder.encode("1123"));
        tecnicoUm.addPerfil(Profile.TECNICO);
        Technical tecnicoDois = new Technical(null,"antonyDD","36863586703","antonyaa@email.com",encoder.encode("1123"));
        tecnicoDois.addPerfil(Profile.ADMIN);
        Technical tecnicoTres = new Technical(null, "Valdir Cezar", "550.482.150-95", "valdir@mail.com", encoder.encode("123"));
        tecnicoTres.addPerfil(Profile.ADMIN);
        Technical tecnicoQuatro = new Technical(null, "Richard Stallman", "903.347.070-56", "stallman@mail.com", encoder.encode("123"));
        Technical tecnicoCinco = new Technical(null, "Claude Elwood Shannon", "271.068.470-54", "shannon@mail.com", encoder.encode("123"));
        Technical tecnicoSeis = new Technical(null, "Tim Berners-Lee", "162.720.120-39", "lee@mail.com", encoder.encode("123"));
        Technical tecnicoSete = new Technical(null, "Linus Torvalds", "778.556.170-27", "linus@mail.com", encoder.encode("123"));

        Client client = new Client(null,"linux", "50219923418","artorias@email.com",encoder.encode("artorias"));
        Client clientUm = new Client(null, "Albert Einstein", "111.661.890-74", "einstein@mail.com", encoder.encode("123"));
        Client clientDois = new Client(null, "Marie Curie", "322.429.140-06", "curie@mail.com", encoder.encode("123"));
        Client clientTres = new Client(null, "Charles Darwin", "792.043.830-62", "darwin@mail.com", encoder.encode("123"));
        Client clientQuatro = new Client(null, "Stephen Hawking", "177.409.680-30", "hawking@mail.com", encoder.encode("123"));
        Client clientCinco = new Client(null, "Max Planck", "081.399.300-83", "planck@mail.com", encoder.encode("123"));

        Call call = new Call(null, Priority.MEDIA, Status.ANDAMENTO, "chamado 00","conexao perdida",tecnico,client);
        Call callUm = new Call(null, Priority.MEDIA, Status.ANDAMENTO, "Chamado 1", "Teste chamado 1", tecnicoTres, clientUm);
        Call callDois = new Call(null, Priority.ALTA, Status.ABERTO, "Chamado 2", "Teste chamado 2", tecnicoSeis, clientDois);
        Call callTres = new Call(null, Priority.BAIXA, Status.ENCERRADO, "Chamado 3", "Teste chamado 3", tecnicoTres, clientTres);
        Call callQuatro = new Call(null, Priority.ALTA, Status.ABERTO, "Chamado 4", "Teste chamado 4", tecnicoQuatro, clientQuatro);
        Call callCinco = new Call(null, Priority.MEDIA, Status.ANDAMENTO, "Chamado 5", "Teste chamado 5", tecnicoCinco, clientCinco);
        Call callSeis = new Call(null, Priority.BAIXA, Status.ENCERRADO, "Chamado 7", "Teste chamado 6", tecnicoSete, client);

        technicalRepository.saveAll(Arrays.asList(tecnico,tecnicoUm,tecnicoDois));
        clientRepository.saveAll(Arrays.asList(client));
        personRepository.saveAll(Arrays.asList(tecnicoTres, tecnicoQuatro, tecnicoCinco, tecnicoSete, tecnicoSeis,  clientUm, clientDois, clientTres, clientQuatro, clientCinco));
        callRepository.saveAll(Arrays.asList(call,callUm,callDois,callTres,callQuatro,callCinco,callSeis));
    }

}
