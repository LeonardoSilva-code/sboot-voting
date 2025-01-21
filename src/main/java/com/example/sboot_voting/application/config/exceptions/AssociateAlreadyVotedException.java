package com.example.sboot_voting.application.config.exceptions;

public class AssociateAlreadyVotedException extends RuntimeException {
    public AssociateAlreadyVotedException(String message) {
        super(message);
    }
}
