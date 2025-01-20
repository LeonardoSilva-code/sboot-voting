package com.example.sboot_voting.application.ports.out;

import com.example.sboot_voting.application.core.domain.Vote;

import java.util.List;
import java.util.UUID;

public interface GetVotesBySessionIdOutputPort {
    List<Vote> execute(UUID votingSessionId);
}
