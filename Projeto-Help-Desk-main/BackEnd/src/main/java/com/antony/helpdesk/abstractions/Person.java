package com.antony.helpdesk.abstractions;

import com.antony.helpdesk.enums.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Entity
public abstract class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer personId;
    protected String name;
    @CPF
    @Column(unique = true)
    protected String cpf;
    @Email
    @Column(unique = true)
    protected String email;
    protected String password;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIS")
    protected Set<Integer> profile = new HashSet<>();
    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate creationDate = LocalDate.now();

    public Person() {
        super();
        addPerfil(Profile.CLIENTE);
    }

    public Person(Integer personId, String name, String cpf, String email, String password) {
        this.personId = personId;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        addPerfil(Profile.CLIENTE);
    }

    public Set<Profile> getProfile() {
        return profile.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Profile perfil){
        this.profile.add(perfil.getCode());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return personId.equals(person.personId) && cpf.equals(person.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, cpf);
    }

}
