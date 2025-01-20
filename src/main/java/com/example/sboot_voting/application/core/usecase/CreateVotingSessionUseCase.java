package com.example.sboot_voting.application.core.usecase;

import com.example.sboot_voting.application.core.domain.Agenda;
import com.example.sboot_voting.application.core.domain.VotingSession;
import com.example.sboot_voting.application.ports.in.CreateVotingSessionInputPort;
import com.example.sboot_voting.application.ports.out.CreateVotingSessionOutputPort;
import com.example.sboot_voting.application.ports.out.GetAgendaByIdOutputPort;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreateVotingSessionUseCase implements CreateVotingSessionInputPort {

    private final CreateVotingSessionOutputPort createVotingSessionOutputPort;

    private final GetAgendaByIdOutputPort getAgendaByIdOutputPort;

    public CreateVotingSessionUseCase(CreateVotingSessionOutputPort createVotingSessionOutputPort, GetAgendaByIdOutputPort getAgendaByIdOutputPort) {
        this.createVotingSessionOutputPort = createVotingSessionOutputPort;
        this.getAgendaByIdOutputPort = getAgendaByIdOutputPort;
    }

    @Override
    public VotingSession execute(VotingSession votingSession, Long votingTimeInMinutes) {
        this.assertAgenda(votingSession.getAgendaId());
        LocalDateTime endDate = this.calculateVotingSessionEndTime(votingSession.getStartDate(), votingTimeInMinutes);
        votingSession.setEndDate(endDate);
        return this.createVotingSessionOutputPort.execute(votingSession);
    }

    private LocalDateTime calculateVotingSessionEndTime(LocalDateTime startDate, Long votingTimeInMinutes) {
        return startDate.plusMinutes(votingTimeInMinutes);
    }

    private void assertAgenda(UUID id){
        Agenda agenda = this.getAgendaByIdOutputPort.execute(id);
        if(agenda == null){
           throw new RuntimeException("Agenda does not exist") ;
        }
    }
}
