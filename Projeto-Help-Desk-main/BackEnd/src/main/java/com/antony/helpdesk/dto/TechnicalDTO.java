package com.antony.helpdesk.dto;

import com.antony.helpdesk.enums.Profile;
import com.antony.helpdesk.model.Technical;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TechnicalDTO implements Serializable {

    protected Integer personId;
    @NotNull(message = "Campo name é requerido")
    protected String name;
    @NotNull(message = "Campo cpf é requerido")
    protected String cpf;
    @NotNull(message = "Campo email é requerido")
    protected String email;
    @NotNull(message = "Campo password é requerido")
    protected String password;
    protected Set<Integer> profile = new HashSet<>();
    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate creationDate = LocalDate.now();

    public TechnicalDTO() {
        super();
    }

    public TechnicalDTO(Technical technical) {
        super();
        this.personId = technical.getPersonId();
        this.name = technical.getName();
        this.cpf = technical.getCpf();
        this.email = technical.getEmail();
        this.password = technical.getPassword();
        this.profile = technical.getProfile().stream().map(x -> x.getCode()).collect(Collectors.toSet());
        this.creationDate =  technical.getCreationDate();
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Profile> getProfile() {
        return profile.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
    }

    public void addProfile(Profile profile) {
        this.profile.add(profile.getCode());
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }


}
