package com.projetooficina.sistemaoficinaback.dto;

import com.projetooficina.sistemaoficinaback.model.Cronograma;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CronogramaDTO {

    private Long idCronograma;
    private Date dataInicio;
    private Date dataFim;
    private String descricao;

    public CronogramaDTO(Cronograma cronograma){
        this.idCronograma = cronograma.getIdCronograma();
        this.dataInicio = cronograma.getDataInicio();
        this.dataFim = cronograma.getDataFim();
        this.idCronograma = cronograma.getIdCronograma();
    }

}
