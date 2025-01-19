package com.example.sboot_voting.application.ports.in;

import com.example.sboot_voting.application.core.domain.Vote;

public interface CreateVoteInputPort {

    Vote execute(Vote vote);
}
