package com.example.sboot_voting.adapters.in.controller;

import com.example.sboot_voting.adapters.in.dto.VotingSessionRequestDTO;
import com.example.sboot_voting.adapters.in.dto.VotingSessionResponseDTO;
import com.example.sboot_voting.adapters.in.mapper.VotingSessionMapper;
import com.example.sboot_voting.application.core.domain.VotingSession;
import com.example.sboot_voting.application.ports.in.CreateVotingSessionInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/v1/voting-session"})
public class VotingSessionController {

    @Autowired
    private CreateVotingSessionInputPort createVotingSessionInputPort;

    @Autowired
    private VotingSessionMapper mapper;

    @PostMapping("")
    VotingSessionResponseDTO create(@RequestBody VotingSessionRequestDTO request){
        VotingSession votingSession = this.mapper.toVotingSession(request);
        VotingSession votingSessionDb = this.createVotingSessionInputPort.execute(votingSession, request.getEndTimeInMinutes());
        return this.mapper.toVotingSessionResponseDTO(votingSessionDb);
    }

}
