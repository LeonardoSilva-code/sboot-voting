package com.example.sboot_voting.usecase;

import com.example.sboot_voting.application.config.exceptions.AgendaNotFoundException;
import com.example.sboot_voting.application.core.domain.Agenda;
import com.example.sboot_voting.application.core.usecase.GetAgendaByIdUseCase;
import com.example.sboot_voting.application.ports.out.GetAgendaByIdOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetAgendaByIdUseCaseTest {

    @Mock
    private GetAgendaByIdOutputPort getAgendaByIdOutputPort;

    @InjectMocks
    private GetAgendaByIdUseCase getAgendaByIdUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAgendaById_Success() {
        UUID agendaId = UUID.randomUUID();
        Agenda agenda = new Agenda();
        agenda.setId(agendaId);
        agenda.setTitle("Test Agenda");

        when(getAgendaByIdOutputPort.execute(agendaId)).thenReturn(agenda);

        Agenda result = getAgendaByIdUseCase.execute(agendaId);

        assertNotNull(result);
        assertEquals(agendaId, result.getId());
        assertEquals("Test Agenda", result.getTitle());
        verify(getAgendaByIdOutputPort, times(1)).execute(agendaId);
    }

    @Test
    void testGetAgendaById_NotFound() {
        UUID agendaId = UUID.randomUUID();
        when(getAgendaByIdOutputPort.execute(agendaId)).thenReturn(null);

        assertThrows(AgendaNotFoundException.class, () -> getAgendaByIdUseCase.execute(agendaId));
        verify(getAgendaByIdOutputPort, times(1)).execute(agendaId);
    }
}