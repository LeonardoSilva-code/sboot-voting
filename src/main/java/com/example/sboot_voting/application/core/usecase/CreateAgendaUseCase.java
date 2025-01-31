package com.example.sboot_voting.application.core.usecase;

import com.example.sboot_voting.adapters.in.controller.VotingSessionController;
import com.example.sboot_voting.application.core.domain.Agenda;
import com.example.sboot_voting.application.ports.in.CreateAgendaInputPort;
import com.example.sboot_voting.application.ports.out.CreateAgendaOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateAgendaUseCase implements CreateAgendaInputPort {

    private static final Logger logger = LoggerFactory.getLogger(CreateAgendaUseCase.class);

    private final CreateAgendaOutputPort saveAgendaOutputPort;

    public CreateAgendaUseCase(CreateAgendaOutputPort saveAgendaOutputPort) {
        this.saveAgendaOutputPort = saveAgendaOutputPort;
    }

    @Override
    public Agenda execute(Agenda agenda){
        logger.info("Creating a new agenda: {}", agenda);
        return this.saveAgendaOutputPort.execute(agenda);
    }
}
