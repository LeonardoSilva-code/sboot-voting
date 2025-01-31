package com.example.sboot_voting.adapters.in.dto;

import com.example.sboot_voting.application.core.domain.VoteOption;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class VoteRequestDTO {
    @NotNull(message = "sessionId cannot be null")
    private UUID sessionId;
    @NotNull(message = "sessionId cannot be null")
    private VoteOption vote;
    @NotNull(message = "associateCpf cannot be null")
    private String associateCpf;
}
