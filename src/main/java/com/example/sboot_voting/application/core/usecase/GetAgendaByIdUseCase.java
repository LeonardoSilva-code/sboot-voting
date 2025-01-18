package com.example.sboot_voting.application.core.usecase;

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
        return getAgendaByIdOutputPort.execute(id);
    }
}
