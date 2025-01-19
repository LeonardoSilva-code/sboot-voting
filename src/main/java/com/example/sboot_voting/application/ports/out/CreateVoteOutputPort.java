package com.example.sboot_voting.application.ports.out;

import com.example.sboot_voting.application.core.domain.Vote;

public interface CreateVoteOutputPort {

    Vote execute(Vote vote);
}
