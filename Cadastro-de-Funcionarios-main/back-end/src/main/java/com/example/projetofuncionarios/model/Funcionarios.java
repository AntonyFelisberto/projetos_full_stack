package com.example.projetofuncionarios.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Funcionarios implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String nome;
    private String email;
    private String trabalho;
    private String telefone;
    private String imagemUrl;
    @Column(nullable = false, updatable = false)
    private String codigoEmpregado;


    public Funcionarios() {}

    public Funcionarios(String nome, String email, String trabalho, String telefone, String imagemUrl, String codigoEmpregado) {
        this.nome = nome;
        this.email = email;
        this.trabalho = trabalho;
        this.telefone = telefone;
        this.imagemUrl = imagemUrl;
        this.codigoEmpregado = codigoEmpregado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String name) {
        this.nome = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTrabalho() {
        return trabalho;
    }

    public void setTrabalho(String trabalho) {
        this.trabalho = trabalho;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public String getCodigoEmpregado() {
        return codigoEmpregado;
    }

    public void setCodigoEmpregado(String codigoEmpregado) {
        this.codigoEmpregado = codigoEmpregado;
    }

    public String toString() {

                return "Funcionarios{" +
                        "id=" + id +
                        ", nome=" + nome + '\'' +
                        ", email=" + email + '\'' +
                        ", trabalho=" + trabalho + '\'' +
                        ", telefone=" + telefone + '\'' +
                        ", imagemUrl=" + imagemUrl + '\'' +
                        ", codigoEmpregado=" + codigoEmpregado + '\'' +
                        '}';

    }

}
