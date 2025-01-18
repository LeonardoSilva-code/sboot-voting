package com.example.sboot_voting.adapters.in.controller;

import com.example.sboot_voting.adapters.in.dto.CreateAgendaRequestDTO;
import com.example.sboot_voting.adapters.in.dto.CreateAgendaResponseDTO;
import com.example.sboot_voting.adapters.in.mapper.AgendaMapper;
import com.example.sboot_voting.application.core.domain.Agenda;
import com.example.sboot_voting.application.ports.in.CreateAgendaInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/agenda")
public class AgendaController {

    @Autowired
    private CreateAgendaInputPort createAgendaInputPort;

    @Autowired
    private AgendaMapper mapper;

    @PostMapping()
    CreateAgendaResponseDTO create(@RequestBody CreateAgendaRequestDTO input){
        Agenda agenda = mapper.toAgenda(input);
        Agenda agendaDb = createAgendaInputPort.execute(agenda);
        return this.mapper.toCreateAgendaResponseDTO(agendaDb);
    }

}
