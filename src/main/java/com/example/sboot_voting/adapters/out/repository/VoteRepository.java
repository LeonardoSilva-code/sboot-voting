package com.example.sboot_voting.adapters.out.repository;

import com.example.sboot_voting.adapters.out.entity.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VoteRepository extends JpaRepository<VoteEntity, UUID> {
    Optional<VoteEntity> findBySessionIdAndAssociateCpf(UUID sessionId, String associateCpf);
    List<VoteEntity> findAllBySessionId(UUID sessionId);
}
