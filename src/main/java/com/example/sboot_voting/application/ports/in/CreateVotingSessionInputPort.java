package com.example.sboot_voting.application.ports.in;

import com.example.sboot_voting.application.core.domain.VotingSession;


public interface CreateVotingSessionInputPort {
    VotingSession execute(VotingSession votingSession, Long votingTimeInMinutes);
}
