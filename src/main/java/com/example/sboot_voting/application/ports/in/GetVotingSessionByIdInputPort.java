package com.example.sboot_voting.application.ports.in;

import com.example.sboot_voting.application.core.domain.VotingSession;

import java.util.UUID;

public interface GetVotingSessionByIdInputPort {
    VotingSession execute(UUID id);
}
