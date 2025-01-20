package com.example.sboot_voting.application.core.usecase;

import com.example.sboot_voting.application.core.domain.VotingSession;
import com.example.sboot_voting.application.ports.in.GetVotingSessionByIdInputPort;
import com.example.sboot_voting.application.ports.out.GetVotingSessionByIdOutputPort;

import java.util.UUID;

public class GetVotingSessionByIdUseCase implements GetVotingSessionByIdInputPort {

    private final GetVotingSessionByIdOutputPort getVotingSessionByIdOutputPort;

    public GetVotingSessionByIdUseCase(GetVotingSessionByIdOutputPort getVotingSessionByIdOutputPort) {
        this.getVotingSessionByIdOutputPort = getVotingSessionByIdOutputPort;
    }

    @Override
    public VotingSession execute(UUID id) {
        return this.getVotingSessionByIdOutputPort.execute(id);
    }
}
