package com.example.sboot_voting.adapters.in.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class VotingSessionRequestDTO {
    private UUID agendaId;
    private Long endTimeInMinutes;
}
