package com.example.sboot_voting.adapters.out;

import com.example.sboot_voting.adapters.out.entity.VoteEntity;
import com.example.sboot_voting.adapters.out.mapper.VoteEntityMapper;
import com.example.sboot_voting.adapters.out.repository.VoteRepository;
import com.example.sboot_voting.application.core.domain.Vote;
import com.example.sboot_voting.application.ports.out.GetVotesBySessionIdOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class GetVotesBySessionIdAdapder implements GetVotesBySessionIdOutputPort {

    @Autowired
    private VoteRepository repository;

    @Autowired
    private VoteEntityMapper mapper;

    @Override
    public List<Vote> execute(UUID votingSessionId) {
        List<VoteEntity> voteEntityList = this.repository.findAllBySessionId(votingSessionId);
        return voteEntityList.stream()
                             .map(voteEntity -> this.mapper.toVote(voteEntity))
                             .toList();
    }
}
