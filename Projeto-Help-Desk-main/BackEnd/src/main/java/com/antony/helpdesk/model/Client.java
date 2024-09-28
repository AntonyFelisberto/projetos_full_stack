package com.antony.helpdesk.model;

import com.antony.helpdesk.abstractions.Person;
import com.antony.helpdesk.dto.ClientDTO;
import com.antony.helpdesk.enums.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
public class Client extends Person {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Call> chamados = new ArrayList<>();

    public Client() {
        super();
        addPerfil(Profile.CLIENTE);
    }

    public Client(Integer personId, String name, String cpf, String email, String password) {
        super(personId, name, cpf, email, password);
        addPerfil(Profile.CLIENTE);
    }

    public Client(ClientDTO clientDto) {
        super();
        this.personId = clientDto.getPersonId();
        this.name = clientDto.getName();
        this.cpf = clientDto.getCpf();
        this.email = clientDto.getEmail();
        this.password = clientDto.getPassword();
        this.profile = clientDto.getProfile().stream().map(x -> x.getCode()).collect(Collectors.toSet());
        this.creationDate =  clientDto.getCreationDate();
    }

}
