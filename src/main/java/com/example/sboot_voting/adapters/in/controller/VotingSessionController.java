package com.example.sboot_voting.adapters.in.controller;

import com.example.sboot_voting.adapters.in.dto.VotingSessionRequestDTO;
import com.example.sboot_voting.adapters.in.dto.VotingSessionResponseDTO;
import com.example.sboot_voting.adapters.in.mapper.VotingSessionMapper;
import com.example.sboot_voting.application.core.domain.VotingSession;
import com.example.sboot_voting.application.core.domain.VotingSessionResult;
import com.example.sboot_voting.application.ports.in.CreateVotingSessionInputPort;
import com.example.sboot_voting.application.ports.in.GetVotingSessionByIdInputPort;
import com.example.sboot_voting.application.ports.in.GetVotingSessionResultInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping({"/api/v1/voting-session"})
@Tag(name = "Voting Session Controller", description = "Endpoints for managing voting sessions")
public class VotingSessionController {

    private static final Logger logger = LoggerFactory.getLogger(VotingSessionController.class);

    @Autowired
    private CreateVotingSessionInputPort createVotingSessionInputPort;

    @Autowired
    private GetVotingSessionByIdInputPort getVotingSessionByIdInputPort;

    @Autowired
    private GetVotingSessionResultInputPort getVotingSessionResultInputPort;

    @Autowired
    private VotingSessionMapper mapper;

    @PostMapping("")
    @Operation(summary = "Create a new voting session", description = "Registers a new voting session")
    VotingSessionResponseDTO create(@Valid @RequestBody VotingSessionRequestDTO request){
        logger.info("Received request to create a new Voting Session: {}", request);

        VotingSession votingSession = this.mapper.toVotingSession(request);
        logger.debug("Mapped request to Voting Session: {}", votingSession);

        VotingSession votingSessionDb = this.createVotingSessionInputPort.execute(votingSession, request.getEndTimeInMinutes());
        logger.info("Voting Session created successfully with ID: {}", votingSessionDb.getId());

        return this.mapper.toVotingSessionResponseDTO(votingSessionDb);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a voting session", description = "Get a voting session by id")
    VotingSessionResponseDTO getById(@PathVariable("id") UUID id){
        logger.info("Received request to get Voting Session by ID: {}", id);

        VotingSession votingSession = this.getVotingSessionByIdInputPort.execute(id);
        if (votingSession == null) {
            logger.warn("Voting Session with ID {} not found", id);
        } else {
            logger.debug("Voting Session found: {}", votingSession);
        }

        return this.mapper.toVotingSessionResponseDTO(votingSession);
    }

    @GetMapping("result/{id}")
    @Operation(summary = "Get a voting session result", description = "Get the result of a voting session by voting session id")
    VotingSessionResult getVotingResult(@PathVariable("id") UUID id){
        logger.info("Received request to get Voting Session Result by ID: {}", id);
        return this.getVotingSessionResultInputPort.execute(id);
    }

}
