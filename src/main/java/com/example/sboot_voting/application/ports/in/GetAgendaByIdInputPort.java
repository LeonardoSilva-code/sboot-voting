package com.example.sboot_voting.application.ports.in;

import com.example.sboot_voting.application.core.domain.Agenda;

import java.util.UUID;

public interface GetAgendaByIdInputPort {

    Agenda execute(UUID id);
}
