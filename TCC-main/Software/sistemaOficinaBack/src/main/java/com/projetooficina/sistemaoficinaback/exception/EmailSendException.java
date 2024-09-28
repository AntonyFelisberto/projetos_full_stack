package com.projetooficina.sistemaoficinaback.exception;

public class EmailSendException extends RuntimeException{

    public EmailSendException(String message) {
        super(message);
    }

    public EmailSendException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
