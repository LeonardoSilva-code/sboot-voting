package com.example.sboot_voting.application.core.usecase;

import com.example.sboot_voting.application.config.exceptions.GenericException;
import com.example.sboot_voting.application.core.domain.VotingSession;
import com.example.sboot_voting.application.ports.in.GetVotingSessionByIdInputPort;
import com.example.sboot_voting.application.ports.out.GetVotingSessionByIdOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class GetVotingSessionByIdUseCase implements GetVotingSessionByIdInputPort {

    private static final Logger logger = LoggerFactory.getLogger(GetVotingSessionByIdUseCase.class);

    private final GetVotingSessionByIdOutputPort getVotingSessionByIdOutputPort;

    public GetVotingSessionByIdUseCase(GetVotingSessionByIdOutputPort getVotingSessionByIdOutputPort) {
        this.getVotingSessionByIdOutputPort = getVotingSessionByIdOutputPort;
    }

    @Override
    public VotingSession execute(UUID id) {
        logger.info("Starting process to retrieve voting session by ID: {}", id);

        try {
            VotingSession votingSession = this.getVotingSessionByIdOutputPort.execute(id);

            if (votingSession == null) {
                logger.warn("Voting session not found for ID: {}", id);
            } else {
                logger.info("Voting session retrieved successfully for ID: {}", id);
            }

            return votingSession;
        } catch (Exception e) {
            logger.error("Unexpected error retrieving voting session: {}", e.getMessage(), e);
            throw new GenericException("Unexpected error retrieving voting session" + e.getMessage());
        }
    }
}