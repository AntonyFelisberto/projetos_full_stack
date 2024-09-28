package com.antony.helpdesk.services;

import com.antony.helpdesk.abstractions.Person;
import com.antony.helpdesk.dto.TechnicalDTO;
import com.antony.helpdesk.exceptions.DataIntegrityException;
import com.antony.helpdesk.exceptions.NotFoundException;
import com.antony.helpdesk.model.Technical;
import com.antony.helpdesk.repositories.PersonRepository;
import com.antony.helpdesk.repositories.TechnicalRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TechnicalServices {

    private TechnicalRepository technicalRepository;

    private PersonRepository personRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Technical findById(Integer id){
        return technicalRepository.findById(id).orElseThrow(() -> new NotFoundException("Objeto não encontrado pelo id: "+id));
    }

    public List<Technical> findAll() {
        return technicalRepository.findAll();
    }


    public Technical create(TechnicalDTO technicalDto) {
        technicalDto.setPersonId(null);
        technicalDto.setPassword(bCryptPasswordEncoder.encode(technicalDto.getPassword()));
        validarPorCpfEmail(technicalDto);
        return technicalRepository.save(new Technical(technicalDto));
    }

    public Technical update(Integer id, TechnicalDTO technicalDTO) {
        technicalDTO.setPersonId(null);
        Technical technical = findById(id);

        if(!technicalDTO.getPassword().equals(technical.getPassword())){
            technical.setPassword(bCryptPasswordEncoder.encode(technicalDTO.getPassword()));
        }

        validarPorCpfEmail(technicalDTO);
        technical = new Technical(technicalDTO);
        return technicalRepository.save(technical);
    }

    private void validarPorCpfEmail(TechnicalDTO technicalDto) {
        Optional<Person> personCpf = personRepository.findByCpf(technicalDto.getCpf());
        Optional<Person> personEmail = personRepository.findByEmail(technicalDto.getEmail());
        
        if(personCpf.isPresent() && personCpf.get().getPersonId() != technicalDto.getPersonId()){
            throw new DataIntegrityException("CPF ja cadastrado no sistema");
        } else if (personEmail.isPresent() && personEmail.get().getPersonId() != technicalDto.getPersonId()) {
            throw new DataIntegrityException("Email ja cadastrado no sistema");
        }
    }

    public void delete(Integer id) {
        Technical technical = findById(id);
        if(technical.getChamados().size() > 0){
            throw new DataIntegrityException("Tecnico possui ordens de serviço e não pode ser deletado");
        }else{
            technicalRepository.deleteById(id);
        }
    }

}
