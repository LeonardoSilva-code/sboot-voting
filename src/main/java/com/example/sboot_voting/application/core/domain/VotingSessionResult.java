package com.example.sboot_voting.application.core.domain;

import java.util.UUID;

public class VotingSessionResult {

    public VotingSessionResult() {
    }

    public VotingSessionResult(UUID votingSessionId, Long totalVotes, Long inFavor, Long agaist, VoteFinalResult result, boolean isOpen) {
        this.votingSessionId = votingSessionId;
        this.totalVotes = totalVotes;
        this.inFavor = inFavor;
        this.agaist = agaist;
        this.result = result;
        this.isOpen = isOpen;
    }

    private UUID votingSessionId;
    private Long totalVotes;
    private Long inFavor;
    private Long agaist;
    private boolean isOpen;
    private VoteFinalResult result;

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public UUID getVotingSessionId() {
        return votingSessionId;
    }

    public void setVotingSessionId(UUID agendaId) {
        this.votingSessionId = agendaId;
    }

    public Long getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(Long totalVotes) {
        this.totalVotes = totalVotes;
    }

    public Long getInFavor() {
        return inFavor;
    }

    public void setInFavor(Long inFavor) {
        this.inFavor = inFavor;
    }

    public Long getAgaist() {
        return agaist;
    }

    public void setAgaist(Long agaist) {
        this.agaist = agaist;
    }

    public VoteFinalResult getResult() {
        return result;
    }

    public void setResult(VoteFinalResult result) {
        this.result = result;
    }
}
