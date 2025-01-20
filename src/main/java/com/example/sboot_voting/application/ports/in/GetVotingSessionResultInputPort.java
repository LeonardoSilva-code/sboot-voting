package com.example.sboot_voting.application.ports.in;

import com.example.sboot_voting.application.core.domain.VotingSessionResult;

import java.util.UUID;

public interface GetVotingSessionResultInputPort {
    VotingSessionResult execute(UUID votingSessionId);
}
