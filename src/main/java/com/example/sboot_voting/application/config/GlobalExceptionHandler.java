package com.example.sboot_voting.application.config;

import com.example.sboot_voting.application.config.exceptions.AgendaNotFoundException;
import com.example.sboot_voting.application.config.exceptions.AssociateAlreadyVotedException;
import com.example.sboot_voting.application.config.exceptions.VotingSessionIsClosedException;
import com.example.sboot_voting.application.config.exceptions.VotingSessionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AgendaNotFoundException.class)
    public ResponseEntity<String> handleAgendaNotFoundException(AgendaNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(VotingSessionNotFoundException.class)
    public ResponseEntity<String> handleVotingSessionNotFoundException(VotingSessionNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(VotingSessionIsClosedException.class)
    public ResponseEntity<String> handleVotingSessionIsClosedException(VotingSessionIsClosedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    @ExceptionHandler(AssociateAlreadyVotedException.class)
    public ResponseEntity<String> handleAssociateAlreadyVotedException(AssociateAlreadyVotedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

}