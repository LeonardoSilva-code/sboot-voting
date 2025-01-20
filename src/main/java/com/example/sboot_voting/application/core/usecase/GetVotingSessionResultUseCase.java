package com.example.sboot_voting.application.core.usecase;

import com.example.sboot_voting.application.core.domain.Vote;
import com.example.sboot_voting.application.core.domain.VoteFinalResult;
import com.example.sboot_voting.application.core.domain.VoteOption;
import com.example.sboot_voting.application.core.domain.VotingSessionResult;
import com.example.sboot_voting.application.ports.in.GetVotingSessionResultInputPort;
import com.example.sboot_voting.application.ports.out.GetVotesBySessionIdOutputPort;

import java.util.List;
import java.util.UUID;

public class GetVotingSessionResultUseCase implements GetVotingSessionResultInputPort {

    public final GetVotesBySessionIdOutputPort getVotesBySessionIdOutputPort;

    public GetVotingSessionResultUseCase(GetVotesBySessionIdOutputPort getVotesBySessionIdOutputPort) {
        this.getVotesBySessionIdOutputPort = getVotesBySessionIdOutputPort;
    }

    @Override
    public VotingSessionResult execute(UUID votingSessionId) {
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
        return new VotingSessionResult(votingSessionId, totalVotes, inFavor, against, result);
    }



}
