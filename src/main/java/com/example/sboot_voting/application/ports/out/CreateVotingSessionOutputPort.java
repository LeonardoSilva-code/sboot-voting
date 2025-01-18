package com.example.sboot_voting.application.ports.out;

import com.example.sboot_voting.application.core.domain.VotingSession;

public interface CreateVotingSessionOutputPort {

    VotingSession execute(VotingSession votingSession);
}
