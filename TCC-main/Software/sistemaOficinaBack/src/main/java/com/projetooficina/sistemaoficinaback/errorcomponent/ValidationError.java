package com.projetooficina.sistemaoficinaback.errorcomponent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationError extends StandardError {

    private List<FieldErroMessage> error = new ArrayList<>();

    public ValidationError(Long timestamp, Integer status, String message) {
        super(timestamp, status, message);
    }

    public void addError(String erro, String message){
        this.error.add(new FieldErroMessage(erro, message));
    }

}
