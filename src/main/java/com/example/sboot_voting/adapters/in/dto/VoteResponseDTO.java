package com.example.sboot_voting.adapters.in.dto;

import com.example.sboot_voting.application.core.domain.VoteOption;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class VoteResponseDTO {
    private UUID id;
    private UUID sessionId;
    private VoteOption vote;
    private String associateCpf;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
