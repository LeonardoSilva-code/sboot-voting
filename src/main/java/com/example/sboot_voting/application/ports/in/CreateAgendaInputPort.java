package com.example.sboot_voting.application.ports.in;

import com.example.sboot_voting.application.core.domain.Agenda;

public interface CreateAgendaInputPort {

    Agenda execute(Agenda agenda);
}
