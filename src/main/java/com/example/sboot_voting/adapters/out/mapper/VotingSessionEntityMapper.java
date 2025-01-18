package com.example.sboot_voting.adapters.out.mapper;

import com.example.sboot_voting.adapters.out.entity.VotingSessionEntity;
import com.example.sboot_voting.application.core.domain.VotingSession;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VotingSessionEntityMapper {

    VotingSessionEntity toVotingSessionEntity(VotingSession votingSession);

    VotingSession toVotingSession(VotingSessionEntity toVotingSessionEntity);
}
