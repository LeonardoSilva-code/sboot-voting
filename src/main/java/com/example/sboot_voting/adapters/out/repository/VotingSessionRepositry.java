package com.example.sboot_voting.adapters.out.repository;

import com.example.sboot_voting.adapters.out.entity.VotingSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VotingSessionRepositry extends JpaRepository<VotingSessionEntity, UUID> {
}
