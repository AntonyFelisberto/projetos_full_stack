package com.projetooficina.sistemaoficinaback.exception;

public class DataFormation extends RuntimeException{

    public DataFormation(String message) {
        super(message);
    }

    public DataFormation(String message, Throwable throwable) {
        super(message, throwable);
    }

}
