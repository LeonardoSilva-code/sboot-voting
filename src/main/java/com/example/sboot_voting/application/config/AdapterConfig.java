package com.example.sboot_voting.application.config;

import com.example.sboot_voting.application.core.usecase.*;
import com.example.sboot_voting.application.ports.out.*;
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
    CreateVotingSessionUseCase createVotingSessionUseCase(CreateVotingSessionOutputPort createVotingSessionOutputPort, GetAgendaByIdOutputPort getAgendaByIdOutputPort){
        return new CreateVotingSessionUseCase(createVotingSessionOutputPort, getAgendaByIdOutputPort);
    }

    @Bean
    CreateVoteUseCase createVoteUseCase(CreateVoteOutputPort createVoteOutputPort, GetVotingSessionByIdOutputPort getVotingSessionByIdOutputPort, GetVoteBySessionIdAndAssociateCpfOutputPort getVoteBySessionIdAndAssociateCpfOutputPort){
        return new CreateVoteUseCase(createVoteOutputPort, getVotingSessionByIdOutputPort, getVoteBySessionIdAndAssociateCpfOutputPort);
    }

    @Bean
    GetVotingSessionByIdUseCase getVotingSessionByIdUseCase(GetVotingSessionByIdOutputPort getVotingSessionByIdOutputPort){
        return new GetVotingSessionByIdUseCase(getVotingSessionByIdOutputPort);
    }

    @Bean
    GetVotingSessionResultUseCase getVotingSessionResultUseCase(GetVotesBySessionIdOutputPort getVotesBySessionIdOutputPort){
        return new GetVotingSessionResultUseCase(getVotesBySessionIdOutputPort);
    }

}
