package com.example.sboot_voting.adapters.in.controller;

import com.example.sboot_voting.adapters.in.dto.VoteRequestDTO;
import com.example.sboot_voting.adapters.in.dto.VoteResponseDTO;
import com.example.sboot_voting.adapters.in.mapper.VoteMapper;
import com.example.sboot_voting.application.core.domain.Vote;
import com.example.sboot_voting.application.ports.in.CreateVoteInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/v1/vote"})
public class VoteController {

    @Autowired
    private CreateVoteInputPort port;

    @Autowired
    private VoteMapper mapper;

    @PostMapping("")
    VoteResponseDTO create(@RequestBody VoteRequestDTO request){
        Vote vote = this.mapper.toVote(request);
        Vote voteDb = this.port.execute(vote);
        return this.mapper.toVoteResponseDTO(voteDb);
    }
}
