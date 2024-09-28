package com.projetooficina.sistemaoficinaback.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.validation.constraints.Email;

import java.io.Serializable;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Usuario implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(nullable = false, updatable = false)
        private Long idUsuario;
        @NotBlank(message = "Nome não pode ser vazio")
        private String nome;
        @NotBlank(message = "Senha não pode ser vazio")
        @Length(min = 8, message = "A senha deverá ter no minimo 8 caracteres")
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private String senha;
        @NotEmpty(message = "Rg não pode ser vazio")
        @Length(min = 9, max = 12, message = "O rg deverá ter no máximo 12 caracteres e no minimo 9")
        private String rg;
        @Column(unique = true)
        @CPF(message = "CPF invalido")
        @NotEmpty(message = "Cpf não pode ser vazio")
        @Length(min = 11, max = 14, message = "O cpf deverá ter no máximo 14 caracteres e no minimo 11")
        private String cpf;
        @Email
        @Column(unique = true)
        @NotBlank(message = "Email não pode ser vazio")
        private String email;
        @NotBlank(message = "Telefone não pode ser vazio")
        @Length(min = 13, max = 14, message = "O telefone deverá ter no máximo 14 caracteres e no minimo 13")
        private String telefone;
        @Column(nullable = false, updatable = false)
        private String codigoEmpregado;
        @NotBlank(message = "Endereço não pode ser vazio")
        @Length(min = 5, message = "O endereco deve ter no minimo 5 caracteres")
        private String endereco;
        @NotBlank(message = "Cargo não pode ser vazio")
        @Length(min = 5, message = "O cargo deve ter no minimo 5 caracteres")
        private String cargo;
        private boolean ativo;
        @Length(min = 2, message = "A carga horaria deve ter no minimo 5 caracteres contendo 00:00")
        @NotBlank(message = "Carga horaria não pode ser vazio")
        private String cargaHoraria;
        @JsonProperty
        @OneToMany(fetch = LAZY, cascade = CascadeType.ALL)
        @PrimaryKeyJoinColumn(name = "idCronograma",referencedColumnName = "idCronograma")
        private List<Cronograma> cronogramas;

}
