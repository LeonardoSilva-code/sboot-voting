package com.example.sboot_voting.adapters.in.controller;

import com.example.sboot_voting.adapters.in.dto.AgendaRequestDTO;
import com.example.sboot_voting.adapters.in.dto.AgendaResponseDTO;
import com.example.sboot_voting.adapters.in.mapper.AgendaMapper;
import com.example.sboot_voting.application.core.domain.Agenda;
import com.example.sboot_voting.application.ports.in.CreateAgendaInputPort;
import com.example.sboot_voting.application.ports.in.GetAgendaByIdInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@RestController
@RequestMapping({"/api/v1/agenda"})
@Tag(name = "Agenda Controller", description = "Endpoints for managing agendas")
public class AgendaController {

    private static final Logger logger = LoggerFactory.getLogger(AgendaController.class);

    @Autowired
    private CreateAgendaInputPort createAgendaInputPort;

    @Autowired
    private GetAgendaByIdInputPort getAgendaByIdInputPort;

    @Autowired
    private AgendaMapper mapper;

    @PostMapping("")
    @Operation(summary = "Create a new agenda", description = "Registers a new agenda")
    public AgendaResponseDTO create(@Valid @RequestBody AgendaRequestDTO request){
        logger.info("Received request to create a new agenda: {}", request);

        Agenda agenda = mapper.toAgenda(request);
        logger.debug("Mapped request to Agenda: {}", agenda);

        Agenda agendaDb = createAgendaInputPort.execute(agenda);
        logger.info("Agenda created successfully with ID: {}", agendaDb.getId());

        return this.mapper.toCreateAgendaResponseDTO(agendaDb);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a agenda", description = "Get a agenda by id")
    public AgendaResponseDTO getById(@PathVariable("id") UUID id){
        logger.info("Received request to get agenda by ID: {}", id);

        Agenda agenda = this.getAgendaByIdInputPort.execute(id);
        if (agenda == null) {
            logger.warn("Agenda with ID {} not found", id);
        } else {
            logger.debug("Agenda found: {}", agenda);
        }

        return this.mapper.toCreateAgendaResponseDTO(agenda);
    }

}
