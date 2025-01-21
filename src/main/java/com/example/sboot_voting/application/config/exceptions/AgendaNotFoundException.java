package com.example.sboot_voting.application.config.exceptions;

public class AgendaNotFoundException extends RuntimeException {
    public AgendaNotFoundException(String message) {
        super(message);
    }
}