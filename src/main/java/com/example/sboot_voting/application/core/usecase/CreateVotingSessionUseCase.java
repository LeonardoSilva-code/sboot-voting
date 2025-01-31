package com.example.sboot_voting.application.core.usecase;

import com.example.sboot_voting.application.config.exceptions.AgendaNotFoundException;
import com.example.sboot_voting.application.core.domain.Agenda;
import com.example.sboot_voting.application.core.domain.VotingSession;
import com.example.sboot_voting.application.ports.in.CreateVotingSessionInputPort;
import com.example.sboot_voting.application.ports.out.CreateVotingSessionOutputPort;
import com.example.sboot_voting.application.ports.out.GetAgendaByIdOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreateVotingSessionUseCase implements CreateVotingSessionInputPort {

    private static final Logger logger = LoggerFactory.getLogger(CreateVotingSessionUseCase.class);

    private final CreateVotingSessionOutputPort createVotingSessionOutputPort;

    private final GetAgendaByIdOutputPort getAgendaByIdOutputPort;

    public CreateVotingSessionUseCase(CreateVotingSessionOutputPort createVotingSessionOutputPort, GetAgendaByIdOutputPort getAgendaByIdOutputPort) {
        this.createVotingSessionOutputPort = createVotingSessionOutputPort;
        this.getAgendaByIdOutputPort = getAgendaByIdOutputPort;
    }

    @Override
    public VotingSession execute(VotingSession votingSession, Long votingTimeInMinutes) {
        logger.info("Starting voting session creation process for agenda ID: {}", votingSession.getAgendaId());

        try {
            this.assertAgenda(votingSession.getAgendaId());
            logger.debug("Agenda validated successfully for ID: {}", votingSession.getAgendaId());

            LocalDateTime endDate = this.calculateVotingSessionEndTime(votingSession.getStartDate(), votingTimeInMinutes);
            logger.debug("Calculated voting session end date: {}", endDate);

            votingSession.setEndDate(endDate);
            VotingSession createdSession = this.createVotingSessionOutputPort.execute(votingSession);

            logger.info("Voting session created successfully for agenda ID: {}", votingSession.getAgendaId());
            return createdSession;
        } catch (AgendaNotFoundException e) {
            logger.error("Error during voting session creation: {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error during voting session creation: {}", e.getMessage(), e);
            throw new RuntimeException("Unexpected error during voting session creation", e);
        }
    }

    private LocalDateTime calculateVotingSessionEndTime(LocalDateTime startDate, Long votingTimeInMinutes) {
        logger.debug("Calculating voting session end time. Start date: {}, Voting time in minutes: {}", startDate, votingTimeInMinutes);
        return startDate.plusMinutes(votingTimeInMinutes);
    }

    private void assertAgenda(UUID id) {
        logger.debug("Validating agenda with ID: {}", id);

        Agenda agenda = this.getAgendaByIdOutputPort.execute(id);
        if (agenda == null) {
            logger.warn("Agenda not found for ID: {}", id);
            throw new AgendaNotFoundException("Agenda not found");
        }
    }
}