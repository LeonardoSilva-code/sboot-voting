package com.example.sboot_voting.application.core.usecase;

import com.example.sboot_voting.application.core.domain.VotingSession;
import com.example.sboot_voting.application.ports.in.CreateVotingSessionInputPort;
import com.example.sboot_voting.application.ports.out.CreateVotingSessionOutputPort;
import java.time.LocalDateTime;

public class CreateVotingSessionUseCase implements CreateVotingSessionInputPort {

    private final CreateVotingSessionOutputPort createVotingSessionOutputPort;

    public CreateVotingSessionUseCase(CreateVotingSessionOutputPort createVotingSessionOutputPort) {
        this.createVotingSessionOutputPort = createVotingSessionOutputPort;
    }

    @Override
    public VotingSession execute(VotingSession votingSession, Long votingTimeInMinutes) {
        LocalDateTime endDate = this.calculateVotingSessionEndTime(votingSession.getStartDate(), votingTimeInMinutes);
        votingSession.setEndDate(endDate);
        return this.createVotingSessionOutputPort.execute(votingSession);
    }

    private LocalDateTime calculateVotingSessionEndTime(LocalDateTime startDate, Long votingTimeInMinutes) {
        return startDate.plusMinutes(votingTimeInMinutes);
    }
}
