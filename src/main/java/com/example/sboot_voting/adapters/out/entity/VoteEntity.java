package com.example.sboot_voting.adapters.out.entity;

import com.example.sboot_voting.application.core.domain.VoteOption;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class VoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "session_id")
    private UUID sessionId;
    @Enumerated(EnumType.STRING)
    private VoteOption vote;
    @Column(name = "associate_cpf")
    private String associateCpf;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
