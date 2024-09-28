package com.antony.helpdesk.config;

import com.antony.helpdesk.services.DataBaseServices;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("dev")
@AllArgsConstructor
public class Config {

    private DataBaseServices dataBaseServices;

    @Bean
    public void intanciarData(){
        dataBaseServices.intanciarData();
    }

}
