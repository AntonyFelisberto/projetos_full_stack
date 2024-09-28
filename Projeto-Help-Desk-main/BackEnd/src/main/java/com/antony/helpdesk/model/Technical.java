package com.antony.helpdesk.model;

import com.antony.helpdesk.abstractions.Person;
import com.antony.helpdesk.dto.TechnicalDTO;
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
public class Technical extends Person {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "tecnico")
    private List<Call> chamados = new ArrayList<>();

    public Technical() {
        super();
        addPerfil(Profile.CLIENTE);
    }

    public Technical(Integer personId, String name, String cpf, String email, String password) {
        super(personId, name, cpf, email, password);
    }

    public Technical(TechnicalDTO technical) {
        super();
        this.personId = technical.getPersonId();
        this.name = technical.getName();
        this.cpf = technical.getCpf();
        this.email = technical.getEmail();
        this.password = technical.getPassword();
        this.profile = technical.getProfile().stream().map(x -> x.getCode()).collect(Collectors.toSet());
        this.creationDate =  technical.getCreationDate();
    }

}
