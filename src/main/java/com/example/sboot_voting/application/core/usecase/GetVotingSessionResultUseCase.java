package com.example.sboot_voting.application.core.usecase;

import com.example.sboot_voting.application.config.exceptions.VotingSessionNotFoundException;
import com.example.sboot_voting.application.core.domain.*;
import com.example.sboot_voting.application.ports.in.GetVotingSessionResultInputPort;
import com.example.sboot_voting.application.ports.out.GetVotesBySessionIdOutputPort;
import com.example.sboot_voting.application.ports.out.GetVotingSessionByIdOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class GetVotingSessionResultUseCase implements GetVotingSessionResultInputPort {

    private static final Logger logger = LoggerFactory.getLogger(GetVotingSessionResultUseCase.class);

    public final GetVotesBySessionIdOutputPort getVotesBySessionIdOutputPort;

    public final GetVotingSessionByIdOutputPort getVotingSessionByIdOutputPort;

    public GetVotingSessionResultUseCase(GetVotesBySessionIdOutputPort getVotesBySessionIdOutputPort, GetVotingSessionByIdOutputPort getVotingSessionByIdOutputPort) {
        this.getVotesBySessionIdOutputPort = getVotesBySessionIdOutputPort;
        this.getVotingSessionByIdOutputPort = getVotingSessionByIdOutputPort;
    }

    @Override
    public VotingSessionResult execute(UUID votingSessionId) {
        logger.info("Starting process to retrieve voting session result for session ID: {}", votingSessionId);

        try {
            boolean isVotingSessionOpen = this.getIsVotingSessionOpen(votingSessionId);
            logger.debug("Voting session status for ID {}: {}", votingSessionId, isVotingSessionOpen ? "Open" : "Closed");

            List<Vote> votesList = getVotesBySessionIdOutputPort.execute(votingSessionId);
            logger.debug("Retrieved {} votes for session ID: {}", votesList.size(), votingSessionId);

            long totalVotes = votesList.size();
            long inFavor = votesList.stream()
                    .filter(vote -> vote.getVote() == VoteOption.YES)
                    .count();
            long against = votesList.stream()
                    .filter(vote -> vote.getVote() == VoteOption.NO)
                    .count();

            VoteFinalResult result;
            if (inFavor > against) {
                result = VoteFinalResult.IN_FAVOR;
            } else if (against > inFavor) {
                result = VoteFinalResult.AGAINST;
            } else {
                result = VoteFinalResult.TIE;
            }

            logger.info("Voting session result calculated for session ID: {}. Total votes: {}, In favor: {}, Against: {}, Result: {}",
                    votingSessionId, totalVotes, inFavor, against, result);

            return new VotingSessionResult(votingSessionId, totalVotes, inFavor, against, result, isVotingSessionOpen);
        } catch (VotingSessionNotFoundException e) {
            logger.error("Error retrieving voting session result: {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error retrieving voting session result: {}", e.getMessage(), e);
            throw new RuntimeException("Unexpected error retrieving voting session result", e);
        }
    }

    private boolean getIsVotingSessionOpen(UUID votingSessionId) {
        logger.debug("Checking if voting session is open for session ID: {}", votingSessionId);

        VotingSession votingSession = this.getVotingSessionByIdOutputPort.execute(votingSessionId);
        if (votingSession != null) {
            return LocalDateTime.now().isBefore(votingSession.getEndDate());
        }

        logger.warn("Voting session not found for ID: {}", votingSessionId);
        throw new VotingSessionNotFoundException("Voting session not found");
    }
}