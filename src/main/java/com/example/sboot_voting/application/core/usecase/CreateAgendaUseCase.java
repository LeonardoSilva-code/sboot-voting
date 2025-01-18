package com.example.sboot_voting.application.core.usecase;

import com.example.sboot_voting.application.core.domain.Agenda;
import com.example.sboot_voting.application.ports.in.CreateAgendaInputPort;
import com.example.sboot_voting.application.ports.out.SaveAgendaOutputPort;

public class CreateAgendaUseCase implements CreateAgendaInputPort {

    private final SaveAgendaOutputPort saveAgendaOutputPort;

    public CreateAgendaUseCase(SaveAgendaOutputPort saveAgendaOutputPort) {
        this.saveAgendaOutputPort = saveAgendaOutputPort;
    }

    @Override
    public Agenda execute(Agenda agenda){
        return this.saveAgendaOutputPort.execute(agenda);
    }
}
