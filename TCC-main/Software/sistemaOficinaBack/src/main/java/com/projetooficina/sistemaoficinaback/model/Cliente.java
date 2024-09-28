package com.projetooficina.sistemaoficinaback.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Proxy(lazy = false)
public class Cliente {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long idCliente;
    @NotBlank(message = "O nome não pode ser vazio")
    private String nome;
    @Email
    @NotBlank(message = "Email não pode ser vazio")
    private String email;
    @NotBlank(message = "o nome do veiculo não pode ser vazio")
    private String veiculo;
    @NotBlank(message = "A placa do veiculo não pode ser vazio")
    @Length(min = 7, max = 8, message = "A placa deve ter no máximo 8 caracteres e no minimo 7")
    private String placa;
    @NotBlank(message = "o endereço não pode ser vazio")
    private String endereco;
    @Length(min = 3, message = "A cidade deve ter no minimo 3 caracteres")
    private String cidade;
    @NotBlank(message = "O estado não pode ser vazio")
    @Length(min = 2, message = "O estado deve ter no minimo 2 caracteres")
    private String estado;
    @NotBlank(message = "Telefone não pode ser vazio")
    @Length(min = 12, max = 15, message = "O telefone deverá ter no máximo 15 caracteres e no minimo 13")
    private String telefone;
    @NotBlank(message = "Celular não pode ser vazio")
    @Length(min = 13, max = 14, message = "O Celular deverá ter no máximo 14 caracteres e no minimo 13")
    private String celular;

}
