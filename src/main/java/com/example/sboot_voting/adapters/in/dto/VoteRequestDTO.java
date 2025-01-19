package com.example.sboot_voting.adapters.in.dto;

import com.example.sboot_voting.application.core.domain.VoteOption;
import lombok.Data;

import java.util.UUID;

@Data
public class VoteRequestDTO {

    private UUID sessionId;
    private VoteOption vote;
    private String associateCpf;
}
