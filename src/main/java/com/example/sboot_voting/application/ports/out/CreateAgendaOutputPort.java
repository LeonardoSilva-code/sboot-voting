package com.example.sboot_voting.application.ports.out;

import com.example.sboot_voting.application.core.domain.Agenda;

public interface CreateAgendaOutputPort {

    Agenda execute(Agenda agenda);
}
