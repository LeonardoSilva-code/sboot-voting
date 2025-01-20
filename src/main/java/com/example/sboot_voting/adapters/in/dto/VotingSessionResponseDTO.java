package com.example.sboot_voting.adapters.in.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class VotingSessionResponseDTO {
    private UUID id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private UUID agendaId;
}
