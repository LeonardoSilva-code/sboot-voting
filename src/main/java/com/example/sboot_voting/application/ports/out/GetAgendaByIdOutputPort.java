package com.example.sboot_voting.application.ports.out;

import com.example.sboot_voting.application.core.domain.Agenda;

import java.util.UUID;

public interface GetAgendaByIdOutputPort {

    Agenda execute(UUID id);
}
