package com.example.sboot_voting.adapters.out;

import com.example.sboot_voting.adapters.out.entity.AgendaEntity;
import com.example.sboot_voting.adapters.out.mapper.AgendaEntityMapper;
import com.example.sboot_voting.adapters.out.repository.AgendaRepository;
import com.example.sboot_voting.application.core.domain.Agenda;
import com.example.sboot_voting.application.ports.out.GetAgendaByIdOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class GetAgendaByIdAdaptor implements GetAgendaByIdOutputPort {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private AgendaEntityMapper agendaEntityMapper;

    @Override
    public Agenda execute(UUID id) {
       Optional<AgendaEntity> agenda = this.agendaRepository.findById(id);
        return agenda.map(agendaEntity -> this.agendaEntityMapper.toAgenda(agendaEntity)).orElse(null);
    }

}
