package com.example.sboot_voting.application.config.exceptions;

public class VotingSessionIsClosedException extends RuntimeException {
    public VotingSessionIsClosedException(String message) {
        super(message);
    }
}
