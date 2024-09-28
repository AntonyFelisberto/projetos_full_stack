package com.projetooficina.sistemaoficinaback.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Materiais {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long idMaterial;
    @Min(1)
    private Integer unidade;
    @Min(0)
    private Integer quantidade;
    @Min(1)
    private Integer codProduto;
    @Lob
    @NotBlank(message = "Descriminizacao não pode ser vazio")
    private String descriminizacao;
    @DecimalMin("0.5")
    private Double precoUnidade;

}
