package com.example.sboot_voting.adapters.out.repository;

import com.example.sboot_voting.adapters.out.entity.AgendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AgendaRepository extends JpaRepository<AgendaEntity, UUID> {
}
