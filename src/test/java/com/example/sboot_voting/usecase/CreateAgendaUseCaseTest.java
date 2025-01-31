package com.example.sboot_voting.usecase;

import com.example.sboot_voting.application.core.domain.Agenda;
import com.example.sboot_voting.application.core.usecase.CreateAgendaUseCase;
import com.example.sboot_voting.application.ports.out.CreateAgendaOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateAgendaUseCaseTest {

    @Mock
    private CreateAgendaOutputPort createAgendaOutputPort;

    @InjectMocks
    private CreateAgendaUseCase createAgendaUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAgenda() {
        Agenda agenda = new Agenda();
        agenda.setTitle("Test Agenda");

        when(createAgendaOutputPort.execute(any(Agenda.class))).thenReturn(agenda);

        Agenda createdAgenda = createAgendaUseCase.execute(agenda);

        assertEquals("Test Agenda", createdAgenda.getTitle());
        verify(createAgendaOutputPort, times(1)).execute(any(Agenda.class));
    }
}