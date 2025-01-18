package com.example.sboot_voting.adapters.out.mapper;

import com.example.sboot_voting.adapters.out.entity.AgendaEntity;
import com.example.sboot_voting.application.core.domain.Agenda;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgendaEntityMapper {


    AgendaEntity toAgendaEntity(Agenda agenda);

    Agenda toAgenda(AgendaEntity agendaEntity);
}
