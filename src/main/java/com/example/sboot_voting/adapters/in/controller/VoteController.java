package com.example.sboot_voting.adapters.in.controller;

import com.example.sboot_voting.adapters.in.dto.VoteRequestDTO;
import com.example.sboot_voting.adapters.in.dto.VoteResponseDTO;
import com.example.sboot_voting.adapters.in.mapper.VoteMapper;
import com.example.sboot_voting.application.core.domain.Vote;
import com.example.sboot_voting.application.ports.in.CreateVoteInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/v1/vote"})
@Tag(name = "Vote Controller", description = "Endpoints for managing votes")
public class VoteController {

    private static final Logger logger = LoggerFactory.getLogger(VoteController.class);

    @Autowired
    private CreateVoteInputPort port;

    @Autowired
    private VoteMapper mapper;

    @PostMapping("")
    @Operation(summary = "Create a new vote", description = "Registers a new vote for a session")
    VoteResponseDTO create(@Valid  @RequestBody VoteRequestDTO request){
        logger.info("Received request to create a new vote: {}", request);

        Vote vote = this.mapper.toVote(request);
        logger.debug("Mapped request to Vote: {}", vote);

        Vote voteDb = this.port.execute(vote);
        logger.info("Agenda created successfully with ID: {}", voteDb.getId());

        return this.mapper.toVoteResponseDTO(voteDb);
    }
}
