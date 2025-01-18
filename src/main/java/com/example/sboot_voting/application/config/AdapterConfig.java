package com.example.sboot_voting.application.config;

import com.example.sboot_voting.application.core.usecase.CreateAgendaUseCase;
import com.example.sboot_voting.application.core.usecase.CreateVotingSessionUseCase;
import com.example.sboot_voting.application.core.usecase.GetAgendaByIdUseCase;
import com.example.sboot_voting.application.ports.out.CreateVotingSessionOutputPort;
import com.example.sboot_voting.application.ports.out.GetAgendaByIdOutputPort;
import com.example.sboot_voting.application.ports.out.CreateAgendaOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterConfig {

    @Bean
    CreateAgendaUseCase createAgendaUseCase(CreateAgendaOutputPort saveAgendaOutputPort){
        return new CreateAgendaUseCase(saveAgendaOutputPort);
    }

    @Bean
    GetAgendaByIdUseCase getAgendaByIdUseCase(GetAgendaByIdOutputPort getAgendaByIdOutputPort){
        return new GetAgendaByIdUseCase(getAgendaByIdOutputPort);
    }

    @Bean
    CreateVotingSessionUseCase createVotingSessionUseCase(CreateVotingSessionOutputPort createVotingSessionOutputPort){
        return new CreateVotingSessionUseCase(createVotingSessionOutputPort);
    }

}
