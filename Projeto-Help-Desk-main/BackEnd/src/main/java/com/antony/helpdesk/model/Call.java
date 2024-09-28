package com.antony.helpdesk.model;

import com.antony.helpdesk.enums.Priority;
import com.antony.helpdesk.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Data
@Entity
public class Call implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOpening = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateClosed;
    private Priority priority;
    private Status status;
    private String title;
    private String observations;

    @ManyToOne
    private Technical tecnico;
    @ManyToOne
    private Client cliente;

    public Call() {
        super();
    }


    public Call(Integer id, Priority priority, Status status, String title, String observations, Technical tecnico, Client cliente) {
        super();
        this.id = id;
        this.priority = priority;
        this.status = status;
        this.title = title;
        this.observations = observations;
        this.tecnico = tecnico;
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Call chamado)) return false;
        return id.equals(chamado.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
