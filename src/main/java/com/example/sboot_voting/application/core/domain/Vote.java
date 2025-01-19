package com.example.sboot_voting.application.core.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Vote {

    public Vote() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Vote(UUID id, UUID sessionId, VoteOption vote, String associateCpf, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.sessionId = sessionId;
        this.vote = vote;
        this.associateCpf = associateCpf;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private UUID id;
    private UUID sessionId;
    private VoteOption vote;
    private String associateCpf;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getSessionId() {
        return sessionId;
    }

    public void setSessionId(UUID sessionId) {
        this.sessionId = sessionId;
    }

    public VoteOption getVote() {
        return vote;
    }

    public void setVote(VoteOption vote) {
        this.vote = vote;
    }

    public String getAssociateCpf() {
        return associateCpf;
    }

    public void setAssociateCpf(String associateCpf) {
        this.associateCpf = associateCpf;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
