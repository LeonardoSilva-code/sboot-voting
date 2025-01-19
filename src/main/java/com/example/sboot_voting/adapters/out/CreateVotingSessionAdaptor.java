package com.example.sboot_voting.adapters.out;

import com.example.sboot_voting.adapters.out.entity.VotingSessionEntity;
import com.example.sboot_voting.adapters.out.mapper.VotingSessionEntityMapper;
import com.example.sboot_voting.adapters.out.repository.VotingSessionRepositry;
import com.example.sboot_voting.application.core.domain.VotingSession;
import com.example.sboot_voting.application.ports.out.CreateVotingSessionOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateVotingSessionAdaptor implements CreateVotingSessionOutputPort {

    @Autowired
    private VotingSessionRepositry votingSessionRepositry;

    @Autowired
    private VotingSessionEntityMapper votingSessionEntityMapper;

    @Override
    public VotingSession execute(VotingSession votingSession) {
        VotingSessionEntity votingSessionEntity = votingSessionEntityMapper.toVotingSessionEntity(votingSession);
        VotingSessionEntity votingSessionEntityDb = this.votingSessionRepositry.save(votingSessionEntity);
        return this.votingSessionEntityMapper.toVotingSession(votingSessionEntityDb);
    }
}
