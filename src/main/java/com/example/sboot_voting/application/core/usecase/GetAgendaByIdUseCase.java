package com.example.sboot_voting.application.core.usecase;

import com.example.sboot_voting.application.config.exceptions.AgendaNotFoundException;
import com.example.sboot_voting.application.config.exceptions.GenericException;
import com.example.sboot_voting.application.core.domain.Agenda;
import com.example.sboot_voting.application.ports.in.GetAgendaByIdInputPort;
import com.example.sboot_voting.application.ports.out.GetAgendaByIdOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class GetAgendaByIdUseCase implements GetAgendaByIdInputPort {

    private static final Logger logger = LoggerFactory.getLogger(GetAgendaByIdUseCase.class);

    private final GetAgendaByIdOutputPort getAgendaByIdOutputPort;

    public GetAgendaByIdUseCase(GetAgendaByIdOutputPort getAgendaByIdOutputPort) {
        this.getAgendaByIdOutputPort = getAgendaByIdOutputPort;
    }

    @Override
    public Agenda execute(UUID id) {
        logger.info("Starting process to retrieve agenda by ID: {}", id);

        try {
            Agenda agenda = getAgendaByIdOutputPort.execute(id);
            if (agenda == null) {
                logger.warn("Agenda not found for ID: {}", id);
                throw new AgendaNotFoundException("Agenda not found");
            }

            logger.info("Agenda retrieved successfully for ID: {}", id);
            return agenda;
        } catch (AgendaNotFoundException e) {
            logger.error("Error retrieving agenda: {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error retrieving agenda: {}", e.getMessage(), e);
            throw new GenericException("Unexpected error retrieving agenda" + e.getMessage());
        }
    }
}