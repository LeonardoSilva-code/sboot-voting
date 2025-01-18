package com.example.sboot_voting.application.core.usecase;

import com.example.sboot_voting.application.core.domain.Agenda;
import com.example.sboot_voting.application.ports.in.CreateAgendaInputPort;
import com.example.sboot_voting.application.ports.out.CreateAgendaOutputPort;

public class CreateAgendaUseCase implements CreateAgendaInputPort {

    private final CreateAgendaOutputPort saveAgendaOutputPort;

    public CreateAgendaUseCase(CreateAgendaOutputPort saveAgendaOutputPort) {
        this.saveAgendaOutputPort = saveAgendaOutputPort;
    }

    @Override
    public Agenda execute(Agenda agenda){
        return this.saveAgendaOutputPort.execute(agenda);
    }
}
