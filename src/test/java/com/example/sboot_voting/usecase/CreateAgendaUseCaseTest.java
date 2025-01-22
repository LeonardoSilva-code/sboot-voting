package com.example.sboot_voting.usecase;

import com.example.sboot_voting.application.core.domain.Agenda;
import com.example.sboot_voting.application.core.usecase.CreateAgendaUseCase;
import com.example.sboot_voting.application.ports.out.CreateAgendaOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class CreateAgendaUseCaseTest {
    private CreateAgendaUseCase createAgendaUseCase;

    @Mock
    private CreateAgendaOutputPort saveAgendaOutputPort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createAgendaUseCase = new CreateAgendaUseCase(saveAgendaOutputPort);
    }

    @Test
    void executeShouldSaveAgenda() {
        Agenda inputAgenda = new Agenda();
        String agendaTitleMock = "Test Agenda";
        UUID agendaIdMock = UUID.randomUUID();
        inputAgenda.setId(agendaIdMock);
        inputAgenda.setTitle(agendaTitleMock);

        Agenda savedAgenda = new Agenda();
        savedAgenda.setId(agendaIdMock);
        savedAgenda.setTitle(agendaTitleMock);

        Mockito.when(saveAgendaOutputPort.execute(any(Agenda.class))).thenReturn(savedAgenda);

        Agenda result = createAgendaUseCase.execute(inputAgenda);

        assertEquals(savedAgenda, result);
        verify(saveAgendaOutputPort).execute(inputAgenda);
    }

    @Test
    void executeShouldHandleNullAgenda() {
        Mockito.when(saveAgendaOutputPort.execute(null)).thenReturn(null);
        Agenda result = createAgendaUseCase.execute(null);
        assertEquals(null, result);
        verify(saveAgendaOutputPort).execute(null);
    }
}
