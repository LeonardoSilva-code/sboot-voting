package com.example.sboot_voting.application.core.usecase;

import com.example.sboot_voting.application.core.domain.Vote;
import com.example.sboot_voting.application.ports.in.CreateVoteInputPort;
import com.example.sboot_voting.application.ports.out.CreateVoteOutputPort;

public class CreateVoteUseCase implements CreateVoteInputPort {

    private final CreateVoteOutputPort createVoteOutputPort;

    public CreateVoteUseCase(CreateVoteOutputPort createVoteOutputPort) {
        this.createVoteOutputPort = createVoteOutputPort;
    }

    @Override
    public Vote execute(Vote vote) {
        this.assertVoteSession();
        this.assertCanVote();
        return this.createVoteOutputPort.execute(vote);
    }

    private void assertVoteSession(){
        //TODO Get voting session by id
    }

    private void assertCanVote(){
        //TODO get votes by cpf and session_id
    }

}
