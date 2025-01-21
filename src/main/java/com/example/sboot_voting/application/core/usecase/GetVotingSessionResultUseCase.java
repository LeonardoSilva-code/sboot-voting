package com.example.sboot_voting.application.core.usecase;

import com.example.sboot_voting.application.config.exceptions.VotingSessionNotFoundException;
import com.example.sboot_voting.application.core.domain.*;
import com.example.sboot_voting.application.ports.in.GetVotingSessionResultInputPort;
import com.example.sboot_voting.application.ports.out.GetVotesBySessionIdOutputPort;
import com.example.sboot_voting.application.ports.out.GetVotingSessionByIdOutputPort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class GetVotingSessionResultUseCase implements GetVotingSessionResultInputPort {

    public final GetVotesBySessionIdOutputPort getVotesBySessionIdOutputPort;

    public final GetVotingSessionByIdOutputPort getVotingSessionByIdOutputPort;

    public GetVotingSessionResultUseCase(GetVotesBySessionIdOutputPort getVotesBySessionIdOutputPort, GetVotingSessionByIdOutputPort getVotingSessionByIdOutputPort) {
        this.getVotesBySessionIdOutputPort = getVotesBySessionIdOutputPort;
        this.getVotingSessionByIdOutputPort = getVotingSessionByIdOutputPort;
    }

    @Override
    public VotingSessionResult execute(UUID votingSessionId) {
        boolean IsVotingSessionOpen = this.getIsVotingSessionOpen(votingSessionId);
        List<Vote> votesList = getVotesBySessionIdOutputPort.execute(votingSessionId);
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
        return new VotingSessionResult(votingSessionId, totalVotes, inFavor, against, result, IsVotingSessionOpen);
    }

    private boolean getIsVotingSessionOpen(UUID votingSessionId){
        VotingSession votingSession = this.getVotingSessionByIdOutputPort.execute(votingSessionId);
        if(votingSession != null){
            return LocalDateTime.now().isBefore(votingSession.getEndDate());
        }
        throw new VotingSessionNotFoundException("Voting session not found");
    }

}
