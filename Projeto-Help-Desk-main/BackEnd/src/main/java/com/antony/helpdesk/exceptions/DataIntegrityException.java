package com.antony.helpdesk.exceptions;

public class DataIntegrityException extends RuntimeException{

    public DataIntegrityException(String message){
        super(message);
    }

    public DataIntegrityException(String message, Throwable throwable){
        super(message,throwable);
    }

}
