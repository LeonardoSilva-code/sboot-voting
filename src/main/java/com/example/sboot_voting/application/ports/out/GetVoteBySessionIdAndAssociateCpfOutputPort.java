package com.example.sboot_voting.application.ports.out;

import com.example.sboot_voting.application.core.domain.Vote;

import java.util.UUID;

public interface GetVoteBySessionIdAndAssociateCpfOutputPort {
    Vote execute(UUID votingSession, String associateCpf);
}
