package com.example.backend.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController                     //método que facilita as requisições e que resume o uso do @responseBody
@RequestMapping("/api/hello")   //url de requisição principal
public class HelloController {

    @GetMapping
    /*  É POSSIVEL ESPECIFICAR UM @RequestMapping com
        @RequestMapping(method = RequestMethod.METODO_QUE_VOCE_QUER,produces=TIPO ) //O produces É O TIPO DE RETORNO QUE VAI GERAR COMO JSON XML ETC
        OU UMA LISTA DELES (method = {RequestMethod.METODO_QUE_VOCE_QUER,})
    */
    public String hello(){
        return "hello world";
    }

}
