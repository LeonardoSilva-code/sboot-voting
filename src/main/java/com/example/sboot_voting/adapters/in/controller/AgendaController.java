package com.example.sboot_voting.adapters.in.controller;

import com.example.sboot_voting.adapters.in.dto.AgendaRequestDTO;
import com.example.sboot_voting.adapters.in.dto.AgendaResponseDTO;
import com.example.sboot_voting.adapters.in.mapper.AgendaMapper;
import com.example.sboot_voting.application.core.domain.Agenda;
import com.example.sboot_voting.application.ports.in.CreateAgendaInputPort;
import com.example.sboot_voting.application.ports.in.GetAgendaByIdInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping({"/api/v1/agenda"})
public class AgendaController {

    @Autowired
    private CreateAgendaInputPort createAgendaInputPort;

    @Autowired
    private GetAgendaByIdInputPort getAgendaByIdInputPort;

    @Autowired
    private AgendaMapper mapper;

    @PostMapping("")
    AgendaResponseDTO create(@RequestBody AgendaRequestDTO request){
        Agenda agenda = mapper.toAgenda(request);
        Agenda agendaDb = createAgendaInputPort.execute(agenda);
        return this.mapper.toCreateAgendaResponseDTO(agendaDb);
    }

    @GetMapping("/{id}")
    AgendaResponseDTO getById(@PathVariable("id") UUID id){
        Agenda agenda = this.getAgendaByIdInputPort.execute(id);
        return this.mapper.toCreateAgendaResponseDTO(agenda);
    }

}
