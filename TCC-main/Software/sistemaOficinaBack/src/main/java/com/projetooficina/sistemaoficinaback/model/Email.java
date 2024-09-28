package com.projetooficina.sistemaoficinaback.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {

    private String from;
    private String to;
    private String subject;
    private String text;
    private byte[] pdfByte;

}
