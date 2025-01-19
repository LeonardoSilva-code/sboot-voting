package com.example.sboot_voting.adapters.out;

import com.example.sboot_voting.adapters.out.entity.VoteEntity;
import com.example.sboot_voting.adapters.out.mapper.VoteEntityMapper;
import com.example.sboot_voting.adapters.out.repository.VoteRepository;
import com.example.sboot_voting.application.core.domain.Vote;
import com.example.sboot_voting.application.ports.out.CreateVoteOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateVoteAdaptor implements CreateVoteOutputPort {

    @Autowired
    private VoteRepository repository;

    @Autowired
    private VoteEntityMapper mapper;

    @Override
    public Vote execute(Vote vote) {
        VoteEntity voteEntity = this.mapper.toVoteEntity(vote);
        VoteEntity voteEntityDb = this.repository.save(voteEntity);
        return this.mapper.toVote(voteEntityDb);
    }
}
