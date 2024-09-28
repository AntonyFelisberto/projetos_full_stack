package com.projetooficina.sistemaoficinaback.exception;

public class DataIntegrityViolation extends RuntimeException{

    public DataIntegrityViolation(String message){
        super(message);
    }

    public DataIntegrityViolation(String message, Throwable throwable){
        super(message, throwable);
    }

}
