package com.antony.helpdesk.services;

import com.antony.helpdesk.abstractions.Person;
import com.antony.helpdesk.dto.ClientDTO;
import com.antony.helpdesk.exceptions.DataIntegrityException;
import com.antony.helpdesk.exceptions.NotFoundException;
import com.antony.helpdesk.model.Client;
import com.antony.helpdesk.repositories.ClientRepository;
import com.antony.helpdesk.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServices {

    private ClientRepository clientRepository;

    private PersonRepository personRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Client findById(Integer id){
        return clientRepository.findById(id).orElseThrow(() -> new NotFoundException("Objeto não encontrado pelo id: "+id));
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }


    public Client create(ClientDTO clientDTO) {
        clientDTO.setPersonId(null);
        clientDTO.setPassword(bCryptPasswordEncoder.encode(clientDTO.getPassword()));
        validarPorCpfEmail(clientDTO);
        return clientRepository.save(new Client(clientDTO));
    }

    public Client update(Integer id, ClientDTO clientDto) {
        clientDto.setPersonId(null);
        Client client = findById(id);
        validarPorCpfEmail(clientDto);
        client = new Client(clientDto);
        return clientRepository.save(client);
    }

    private void validarPorCpfEmail(ClientDTO clientDTO) {
        Optional<Person> personCpf = personRepository.findByCpf(clientDTO.getCpf());
        Optional<Person> personEmail = personRepository.findByEmail(clientDTO.getEmail());

        if(personCpf.isPresent() && personCpf.get().getPersonId() != clientDTO.getPersonId()){
            throw new DataIntegrityException("CPF ja cadastrado no sistema");
        } else if (personEmail.isPresent() && personEmail.get().getPersonId() != clientDTO.getPersonId()) {
            throw new DataIntegrityException("Email ja cadastrado no sistema");
        }
    }

    public void delete(Integer id) {
        Client client = findById(id);
        if(client.getChamados().size() > 0){
            throw new DataIntegrityException("Cliente possui ordens de serviço e não pode ser deletado");
        }else{
            clientRepository.deleteById(id);
        }
    }
}
