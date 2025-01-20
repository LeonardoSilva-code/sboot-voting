package com.example.sboot_voting.adapters.out;

import com.example.sboot_voting.adapters.out.entity.VoteEntity;
import com.example.sboot_voting.adapters.out.mapper.VoteEntityMapper;
import com.example.sboot_voting.adapters.out.mapper.VotingSessionEntityMapper;
import com.example.sboot_voting.adapters.out.repository.VoteRepository;
import com.example.sboot_voting.application.core.domain.Vote;
import com.example.sboot_voting.application.ports.out.GetVoteBySessionIdAndAssociateCpfOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class GetVoteBySessionIdAndAssociateCpfAdaptor implements GetVoteBySessionIdAndAssociateCpfOutputPort {

    @Autowired
    private VoteRepository repository;

    @Autowired
    private VoteEntityMapper mapper;

    @Override
    public Vote execute(UUID votingSession, String associateCpf) {
        Optional<VoteEntity> voteEntity = this.repository.findBySessionIdAndAssociateCpf(votingSession, associateCpf);
        return voteEntity.map(vote -> this.mapper.toVote(vote)).orElse(null);
    }
}
