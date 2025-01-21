package com.example.sboot_voting.adapters.in.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class VotingSessionRequestDTO {
    @NotNull(message = "agendaId cannot be null")
    private UUID agendaId;
    @NotBlank(message = "endTimeInMinutes cannot be blank")
    private Long endTimeInMinutes;
}
