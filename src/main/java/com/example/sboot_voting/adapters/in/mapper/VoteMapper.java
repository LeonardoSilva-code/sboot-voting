package com.example.sboot_voting.adapters.in.mapper;

import com.example.sboot_voting.adapters.in.dto.VoteRequestDTO;
import com.example.sboot_voting.adapters.in.dto.VoteResponseDTO;
import com.example.sboot_voting.application.core.domain.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VoteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Vote toVote(VoteRequestDTO voteRequestDTO);

    VoteResponseDTO toVoteResponseDTO(Vote vote);
}
