package com.example.projetofuncionarios.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String mensagem) {
        super(mensagem);
    }
}
