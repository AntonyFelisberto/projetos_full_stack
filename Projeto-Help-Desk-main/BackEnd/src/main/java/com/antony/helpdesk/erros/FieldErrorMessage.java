package com.antony.helpdesk.erros;

import lombok.Data;

import java.io.Serializable;

@Data
public class FieldErrorMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fieldName;
    private String message;

    public FieldErrorMessage() {
        super();
    }

    public FieldErrorMessage(String fieldName, String message){
        super();
        this.fieldName = fieldName;
        this.message = message;
    }

}
