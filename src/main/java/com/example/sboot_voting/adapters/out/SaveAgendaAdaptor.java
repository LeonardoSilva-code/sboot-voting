package com.example.sboot_voting.adapters.out;

import com.example.sboot_voting.adapters.out.entity.AgendaEntity;
import com.example.sboot_voting.adapters.out.mapper.AgendaEntityMapper;
import com.example.sboot_voting.adapters.out.repository.AgendaRepository;
import com.example.sboot_voting.application.core.domain.Agenda;
import com.example.sboot_voting.application.ports.out.SaveAgendaOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveAgendaAdaptor implements SaveAgendaOutputPort {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private AgendaEntityMapper agendaEntityMapper;

    @Override
    public Agenda execute(Agenda agenda) {
        AgendaEntity agendaEntity = agendaEntityMapper.toAgendaEntity(agenda);
        AgendaEntity agendaEntityDb =  this.agendaRepository.save(agendaEntity);
        return agendaEntityMapper.toAgenda(agendaEntityDb);
    }

}
