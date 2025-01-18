package com.example.sboot_voting.application.config;

import com.example.sboot_voting.application.core.usecase.CreateAgendaUseCase;
import com.example.sboot_voting.application.ports.out.SaveAgendaOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterConfig {

    @Bean
    CreateAgendaUseCase createAgendaUseCase(SaveAgendaOutputPort saveAgendaOutputPort){
        return new CreateAgendaUseCase(saveAgendaOutputPort);
    }
}
