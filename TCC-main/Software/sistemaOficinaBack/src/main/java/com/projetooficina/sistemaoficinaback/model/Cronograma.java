package com.projetooficina.sistemaoficinaback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cronograma {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long idCronograma;
    @NotNull
    @DateTimeFormat(pattern = "dd-MMM-yyyy HH:mm:ss")
    private Date dataInicio;
    @NotNull
    @DateTimeFormat(pattern = "dd-MMM-yyyy HH:mm:ss")
    private Date dataFim;
    @NotBlank(message = "Descrição não pode ser vazio")
    private String descricao;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idUsuario",referencedColumnName = "idUsuario")
    private Usuario usuario;

}
