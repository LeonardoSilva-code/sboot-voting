package com.example.sboot_voting.controller;

import com.example.sboot_voting.adapters.in.controller.AgendaController;
import com.example.sboot_voting.adapters.in.dto.AgendaRequestDTO;
import com.example.sboot_voting.adapters.in.dto.AgendaResponseDTO;
import com.example.sboot_voting.adapters.in.mapper.AgendaMapper;
import com.example.sboot_voting.application.core.domain.Agenda;
import com.example.sboot_voting.application.ports.in.CreateAgendaInputPort;
import com.example.sboot_voting.application.ports.in.GetAgendaByIdInputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AgendaControllerTest {

    @Mock
    private CreateAgendaInputPort createAgendaInputPort;

    @Mock
    private GetAgendaByIdInputPort getAgendaByIdInputPort;

    @Mock
    private AgendaMapper agendaMapper;

    @InjectMocks
    private AgendaController agendaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAgenda() {
        AgendaRequestDTO requestDTO = new AgendaRequestDTO();
        requestDTO.setTitle("Test Agenda");

        Agenda agenda = new Agenda();
        agenda.setTitle("Test Agenda");

        AgendaResponseDTO responseDTO = new AgendaResponseDTO();
        responseDTO.setTitle("Test Agenda");

        when(agendaMapper.toAgenda(any(AgendaRequestDTO.class))).thenReturn(agenda);
        when(createAgendaInputPort.execute(any(Agenda.class))).thenReturn(agenda);
        when(agendaMapper.toCreateAgendaResponseDTO(any(Agenda.class))).thenReturn(responseDTO);

        AgendaResponseDTO response = agendaController.create(requestDTO);

        assertEquals("Test Agenda", response.getTitle());
        verify(createAgendaInputPort, times(1)).execute(any(Agenda.class));
    }

    @Test
    void testGetAgendaById() {
        UUID agendaId = UUID.randomUUID();
        Agenda agenda = new Agenda();
        agenda.setId(agendaId);
        agenda.setTitle("Test Agenda");

        AgendaResponseDTO responseDTO = new AgendaResponseDTO();
        responseDTO.setTitle("Test Agenda");

        when(getAgendaByIdInputPort.execute(agendaId)).thenReturn(agenda);
        when(agendaMapper.toCreateAgendaResponseDTO(any(Agenda.class))).thenReturn(responseDTO);

        AgendaResponseDTO response = agendaController.getById(agendaId);

        assertEquals("Test Agenda", response.getTitle());
        verify(getAgendaByIdInputPort, times(1)).execute(agendaId);
    }
}