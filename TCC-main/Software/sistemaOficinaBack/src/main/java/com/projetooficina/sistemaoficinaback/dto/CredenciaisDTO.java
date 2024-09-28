package com.projetooficina.sistemaoficinaback.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CredenciaisDTO {

    @NotBlank(message = "O nome não pode ser vazio")
    private String nome;
    @NotBlank(message = "A senha não pode ser vazia")
    private String senha;

}
