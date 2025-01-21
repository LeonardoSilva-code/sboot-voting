package com.example.sboot_voting.application.config;

import com.example.sboot_voting.application.config.exceptions.AgendaNotFoundException;
import com.example.sboot_voting.application.config.exceptions.AssociateAlreadyVotedException;
import com.example.sboot_voting.application.config.exceptions.VotingSessionIsClosedException;
import com.example.sboot_voting.application.config.exceptions.VotingSessionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}