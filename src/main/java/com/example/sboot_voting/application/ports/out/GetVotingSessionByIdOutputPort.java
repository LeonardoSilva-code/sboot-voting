package com.example.sboot_voting.application.ports.out;

import com.example.sboot_voting.application.core.domain.VotingSession;

import java.util.UUID;

public interface GetVotingSessionByIdOutputPort {
    VotingSession execute(UUID id);
}
