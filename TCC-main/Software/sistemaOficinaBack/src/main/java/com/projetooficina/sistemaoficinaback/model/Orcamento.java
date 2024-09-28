package com.projetooficina.sistemaoficinaback.model;

import io.micrometer.core.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Orcamento {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long idOrcamento;
    @DateTimeFormat(pattern = "dd-MMM-yyyy HH:mm:ss")
    private Date data = new Date();
    @Nullable
    @Lob
    private byte[] pdf;
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idCliente",referencedColumnName = "idCliente")
    private Cliente cliente;

}
