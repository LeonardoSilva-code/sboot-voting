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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping({"/api/v1/voting-session"})
@Tag(name = "Voting Session Controller", description = "Endpoints for managing voting sessions")
public class VotingSessionController {

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
        VotingSession votingSession = this.mapper.toVotingSession(request);
        VotingSession votingSessionDb = this.createVotingSessionInputPort.execute(votingSession, request.getEndTimeInMinutes());
        return this.mapper.toVotingSessionResponseDTO(votingSessionDb);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a voting session", description = "Get a voting session by id")
    VotingSessionResponseDTO getById(@PathVariable("id") UUID id){
        VotingSession votingSession = this.getVotingSessionByIdInputPort.execute(id);
        return this.mapper.toVotingSessionResponseDTO(votingSession);
    }

    @GetMapping("result/{id}")
    @Operation(summary = "Get a voting session result", description = "Get the result of a voting session by voting session id")
    VotingSessionResult getVotingResult(@PathVariable("id") UUID id){
        return this.getVotingSessionResultInputPort.execute(id);
    }

}
