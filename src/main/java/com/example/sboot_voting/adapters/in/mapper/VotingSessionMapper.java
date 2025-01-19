package com.example.sboot_voting.adapters.in.mapper;


import com.example.sboot_voting.adapters.in.dto.VotingSessionRequestDTO;
import com.example.sboot_voting.adapters.in.dto.VotingSessionResponseDTO;
import com.example.sboot_voting.application.core.domain.VotingSession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface VotingSessionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "endDate", ignore = true)
    @Mapping(target = "open", constant = "true")
    @Mapping(target = "startDate", expression = "java(LocalDateTime.now())")
    VotingSession toVotingSession(VotingSessionRequestDTO votingSessionRequestDTO);


    VotingSessionResponseDTO toVotingSessionResponseDTO(VotingSession votingSession);
}
