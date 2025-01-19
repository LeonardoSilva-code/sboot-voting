package com.example.sboot_voting.adapters.out.mapper;


import com.example.sboot_voting.adapters.out.entity.VoteEntity;
import com.example.sboot_voting.application.core.domain.Vote;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VoteEntityMapper {

    VoteEntity toVoteEntity(Vote vote);

    Vote toVote(VoteEntity voteEntity);
}
