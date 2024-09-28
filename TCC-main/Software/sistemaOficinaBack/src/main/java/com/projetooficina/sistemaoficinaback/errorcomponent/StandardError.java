package com.projetooficina.sistemaoficinaback.errorcomponent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardError {

    private Long timestamp;
    private Integer status;
    private String message;

}
