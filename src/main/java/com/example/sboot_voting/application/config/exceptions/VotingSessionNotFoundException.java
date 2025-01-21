package com.example.sboot_voting.application.config.exceptions;

public class VotingSessionNotFoundException extends RuntimeException {
    public VotingSessionNotFoundException(String message) {
        super(message);
    }
}
