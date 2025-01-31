package com.example.sboot_voting.application.core.usecase;

import com.example.sboot_voting.application.config.exceptions.AssociateAlreadyVotedException;
import com.example.sboot_voting.application.config.exceptions.GenericException;
import com.example.sboot_voting.application.config.exceptions.VotingSessionIsClosedException;
import com.example.sboot_voting.application.config.exceptions.VotingSessionNotFoundException;
import com.example.sboot_voting.application.core.domain.Vote;
import com.example.sboot_voting.application.core.domain.VotingSession;
import com.example.sboot_voting.application.ports.in.CreateVoteInputPort;
import com.example.sboot_voting.application.ports.out.CreateVoteOutputPort;
import com.example.sboot_voting.application.ports.out.GetVoteBySessionIdAndAssociateCpfOutputPort;
import com.example.sboot_voting.application.ports.out.GetVotingSessionByIdOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreateVoteUseCase implements CreateVoteInputPort {

    private static final Logger logger = LoggerFactory.getLogger(CreateVoteUseCase.class);

    private final CreateVoteOutputPort createVoteOutputPort;

    private final GetVotingSessionByIdOutputPort getVotingSessionByIdOutputPort;

    private final GetVoteBySessionIdAndAssociateCpfOutputPort getVoteBySessionIdAndAssociateCpfOutputPort;

    public CreateVoteUseCase(CreateVoteOutputPort createVoteOutputPort, GetVotingSessionByIdOutputPort getVotingSessionByIdOutputPort, GetVoteBySessionIdAndAssociateCpfOutputPort getVoteBySessionIdAndAssociateCpfOutputPort) {
        this.createVoteOutputPort = createVoteOutputPort;
        this.getVotingSessionByIdOutputPort = getVotingSessionByIdOutputPort;
        this.getVoteBySessionIdAndAssociateCpfOutputPort = getVoteBySessionIdAndAssociateCpfOutputPort;
    }

    @Override
    public Vote execute(Vote vote) {
        logger.info("Starting vote creation process for session ID: {} and associate CPF: {}", vote.getSessionId(), vote.getAssociateCpf());
        try {
            this.assertVoteSession(vote.getSessionId());
            this.assertCanVote(vote.getSessionId(), vote.getAssociateCpf());

            Vote createdVote = this.createVoteOutputPort.execute(vote);
            logger.info("Vote created successfully for session ID: {} and associate CPF: {}", vote.getSessionId(), vote.getAssociateCpf());

            return createdVote;
        } catch (VotingSessionNotFoundException | VotingSessionIsClosedException | AssociateAlreadyVotedException e) {
            logger.error("Error during vote creation: {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error during vote creation: {}", e.getMessage(), e);
            throw new GenericException("Unexpected error during vote creation: " + e.getMessage());
        }

    }
    private void assertVoteSession(UUID id) {
        logger.debug("Validating voting session with ID: {}", id);

        VotingSession votingSession = this.getVotingSessionByIdOutputPort.execute(id);
        if (votingSession == null) {
            logger.warn("Voting session not found for ID: {}", id);
            throw new VotingSessionNotFoundException("Voting session not found");
        } else if (LocalDateTime.now().isAfter(votingSession.getEndDate())) {
            logger.warn("Voting session is closed for ID: {}", id);
            throw new VotingSessionIsClosedException("Voting session is closed");
        }
        logger.debug("Voting session validated successfully for ID: {}", id);
    }

    private void assertCanVote(UUID id, String associateCpf) {
        logger.debug("Checking if associate with CPF: {} has already voted in session ID: {}", associateCpf, id);

        Vote vote = this.getVoteBySessionIdAndAssociateCpfOutputPort.execute(id, associateCpf);
        if (vote != null) {
            logger.warn("Associate with CPF: {} has already voted in session ID: {}", associateCpf, id);
            throw new AssociateAlreadyVotedException("already voted");
        }

        logger.debug("Associate with CPF: {} is allowed to vote in session ID: {}", associateCpf, id);
    }
}
