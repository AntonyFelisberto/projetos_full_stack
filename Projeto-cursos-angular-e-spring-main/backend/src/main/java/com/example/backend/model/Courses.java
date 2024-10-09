package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
//@Table(name = "Courses") //especifica o nome da tabla, caso não use o padrão é o nome da classe
public class Courses {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name",length = 200,nullable = false)
    private String name;

    @Column(length = 20,nullable = false)
    private String category;

}
