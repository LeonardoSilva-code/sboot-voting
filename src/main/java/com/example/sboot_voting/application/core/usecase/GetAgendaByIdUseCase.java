package com.example.sboot_voting.application.core.usecase;

import com.example.sboot_voting.application.config.exceptions.AgendaNotFoundException;
import com.example.sboot_voting.application.core.domain.Agenda;
import com.example.sboot_voting.application.ports.in.GetAgendaByIdInputPort;
import com.example.sboot_voting.application.ports.out.GetAgendaByIdOutputPort;
import java.util.UUID;

public class GetAgendaByIdUseCase implements GetAgendaByIdInputPort {

    private final GetAgendaByIdOutputPort getAgendaByIdOutputPort;

    public GetAgendaByIdUseCase(GetAgendaByIdOutputPort getAgendaByIdOutputPort) {
        this.getAgendaByIdOutputPort = getAgendaByIdOutputPort;
    }

    @Override
    public Agenda execute(UUID id) {
        Agenda agenda = getAgendaByIdOutputPort.execute(id);
        if(agenda == null){
            throw new AgendaNotFoundException("Agenda not found");
        }
        return agenda;
    }
}
