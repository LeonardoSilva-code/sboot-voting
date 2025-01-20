package com.example.sboot_voting.adapters.out;

import com.example.sboot_voting.adapters.out.entity.VotingSessionEntity;
import com.example.sboot_voting.adapters.out.mapper.VotingSessionEntityMapper;
import com.example.sboot_voting.adapters.out.repository.VotingSessionRepositry;
import com.example.sboot_voting.application.core.domain.VotingSession;
import com.example.sboot_voting.application.ports.out.GetVotingSessionByIdOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class GetVotingSessionByIdAdaptor implements GetVotingSessionByIdOutputPort {

    @Autowired
    private VotingSessionRepositry votingSessionRepositry;

    @Autowired
    private VotingSessionEntityMapper votingSessionEntityMapper;

    @Override
    public VotingSession execute(UUID id) {
        Optional<VotingSessionEntity> votingSessionEntity = this.votingSessionRepositry.findById(id);
        return votingSessionEntity.map(votingSession -> this.votingSessionEntityMapper.toVotingSession(votingSession)).orElse(null);
    }
}
