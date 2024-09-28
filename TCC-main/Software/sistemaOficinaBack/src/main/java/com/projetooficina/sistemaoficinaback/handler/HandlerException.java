package com.projetooficina.sistemaoficinaback.handler;

import com.projetooficina.sistemaoficinaback.errorcomponent.ValidationError;
import com.projetooficina.sistemaoficinaback.exception.DataFormation;
import com.projetooficina.sistemaoficinaback.exception.DataIntegrityViolation;
import com.projetooficina.sistemaoficinaback.exception.EmailSendException;
import com.projetooficina.sistemaoficinaback.exception.ObjectNotFoundException;
import com.projetooficina.sistemaoficinaback.errorcomponent.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException exception){
        StandardError error = new StandardError(System.currentTimeMillis(),
                                                HttpStatus.NOT_FOUND.value(),
                                                exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolation.class)
    public ResponseEntity<StandardError> dataIntegrityViolation(DataIntegrityViolation exception){
        StandardError error = new StandardError(System.currentTimeMillis(),
                                                HttpStatus.BAD_REQUEST.value(),
                                                exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgumentNotValid(MethodArgumentNotValidException exception) {
        ValidationError erro = new ValidationError(System.currentTimeMillis(),
                                                    HttpStatus.BAD_REQUEST.value(),
                                                    exception.getMessage());

        for(FieldError erros: exception.getBindingResult().getFieldErrors()) {
            erro.addError(erros.getField(), erros.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);

    }

    @ExceptionHandler(DataFormation.class)
    public ResponseEntity<StandardError> dataFormation(DataFormation exception){
        StandardError error = new StandardError(System.currentTimeMillis(),
                                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                                exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(EmailSendException.class)
    public ResponseEntity<StandardError> emailSendException(EmailSendException emailSendException){
        StandardError error = new StandardError(System.currentTimeMillis(),
                                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                                emailSendException.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
