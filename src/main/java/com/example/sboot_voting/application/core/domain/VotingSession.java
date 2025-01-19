package com.example.sboot_voting.application.core.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class VotingSession {

    public VotingSession(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public VotingSession(UUID id, boolean isOpen, LocalDateTime startDate, LocalDateTime endDate, UUID agendaId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.isOpen = isOpen;
        this.startDate = startDate;
        this.endDate = endDate;
        this.agendaId = agendaId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private UUID id;
    private boolean isOpen;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private UUID agendaId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public UUID getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(UUID agendaId) {
        this.agendaId = agendaId;
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
