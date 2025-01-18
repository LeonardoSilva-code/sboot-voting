package com.example.sboot_voting.adapters.in.mapper;

import com.example.sboot_voting.adapters.in.dto.AgendaRequestDTO;
import com.example.sboot_voting.adapters.in.dto.AgendaResponseDTO;
import com.example.sboot_voting.application.core.domain.Agenda;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AgendaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Agenda toAgenda(AgendaRequestDTO createAgendaRequestDTO);

    AgendaResponseDTO toCreateAgendaResponseDTO(Agenda agenda);
}
