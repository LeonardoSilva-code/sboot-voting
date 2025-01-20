package com.example.sboot_voting.adapters.in.controller;

import com.example.sboot_voting.adapters.in.dto.VotingSessionRequestDTO;
import com.example.sboot_voting.adapters.in.dto.VotingSessionResponseDTO;
import com.example.sboot_voting.adapters.in.mapper.VotingSessionMapper;
import com.example.sboot_voting.application.core.domain.VotingSession;
import com.example.sboot_voting.application.core.domain.VotingSessionResult;
import com.example.sboot_voting.application.ports.in.CreateVotingSessionInputPort;
import com.example.sboot_voting.application.ports.in.GetVotingSessionByIdInputPort;
import com.example.sboot_voting.application.ports.in.GetVotingSessionResultInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping({"/api/v1/voting-session"})
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
    VotingSessionResponseDTO create(@RequestBody VotingSessionRequestDTO request){
        VotingSession votingSession = this.mapper.toVotingSession(request);
        VotingSession votingSessionDb = this.createVotingSessionInputPort.execute(votingSession, request.getEndTimeInMinutes());
        return this.mapper.toVotingSessionResponseDTO(votingSessionDb);
    }

    @GetMapping("/{id}")
    VotingSessionResponseDTO getById(@PathVariable("id") UUID id){
        VotingSession votingSession = this.getVotingSessionByIdInputPort.execute(id);
        return this.mapper.toVotingSessionResponseDTO(votingSession);
    }

    @GetMapping("result/{id}")
    VotingSessionResult getVotingResult(@PathVariable("id") UUID id){
        return this.getVotingSessionResultInputPort.execute(id);
    }

}
