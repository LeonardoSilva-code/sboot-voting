package com.example.sboot_voting.application.ports.out;

import com.example.sboot_voting.application.core.domain.Agenda;

public interface SaveAgendaOutputPort {

    Agenda execute(Agenda agenda);
}
