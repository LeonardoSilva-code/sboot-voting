package com.example.sboot_voting.application.core.usecase;

import com.example.sboot_voting.application.core.domain.Vote;
import com.example.sboot_voting.application.core.domain.VotingSession;
import com.example.sboot_voting.application.ports.in.CreateVoteInputPort;
import com.example.sboot_voting.application.ports.out.CreateVoteOutputPort;
import com.example.sboot_voting.application.ports.out.GetVoteBySessionIdAndAssociateCpfOutputPort;
import com.example.sboot_voting.application.ports.out.GetVotingSessionByIdOutputPort;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreateVoteUseCase implements CreateVoteInputPort {

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
        this.assertVoteSession(vote.getSessionId());
        this.assertCanVote(vote.getSessionId(), vote.getAssociateCpf());
        return this.createVoteOutputPort.execute(vote);
    }
    private void assertVoteSession(UUID id){
        VotingSession votingSession = this.getVotingSessionByIdOutputPort.execute(id);
        if(votingSession == null){
            throw new RuntimeException("Voting session not found");
        }
        else if(LocalDateTime.now().isAfter(votingSession.getEndDate())){
            throw new RuntimeException("Voting session is closed");
        }
    }

    private void assertCanVote(UUID id, String associateCpf) {
        Vote vote = this.getVoteBySessionIdAndAssociateCpfOutputPort.execute(id, associateCpf);
        if(vote != null){
            throw new RuntimeException("already voted");
        }
    }

}
