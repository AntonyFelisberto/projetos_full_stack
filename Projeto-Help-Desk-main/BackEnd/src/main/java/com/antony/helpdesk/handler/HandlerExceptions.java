package com.antony.helpdesk.handler;

import com.antony.helpdesk.erros.StandardError;
import com.antony.helpdesk.erros.ValidationError;
import com.antony.helpdesk.exceptions.DataIntegrityException;
import com.antony.helpdesk.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class HandlerExceptions {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> notFoundException(NotFoundException exception, HttpServletRequest request){
        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),"objeto não encontrado",exception.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrityViolation(DataIntegrityException exception, HttpServletRequest request){
        StandardError error = new StandardError(System.currentTimeMillis(),
                                                HttpStatus.BAD_REQUEST.value(),
                                                "violação de dados",
                                                exception.getMessage(),
                                                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> dataIntegrityViolation(MethodArgumentNotValidException exception, HttpServletRequest request){
        ValidationError error = new ValidationError(System.currentTimeMillis(),
                                    HttpStatus.BAD_REQUEST.value(),
                                    "Validation Error",
                                    "Erro na validação de dados",
                                    request.getRequestURI());
        for(FieldError erros:exception.getBindingResult().getFieldErrors()){
            error.setErrorMessages(erros.getField(),erros.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
    }

}
