package com.antony.helpdesk.erros;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

    private static final long serialVersionUID = 1L;

    private List<FieldErrorMessage> errorMessages = new ArrayList<>();

    public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public List<FieldErrorMessage> getErrorMessages(){
        return errorMessages;
    }

    public void setErrorMessages(String fieldName,String errorMessages){
        this.errorMessages.add(new FieldErrorMessage(fieldName,errorMessages));
    }

}
