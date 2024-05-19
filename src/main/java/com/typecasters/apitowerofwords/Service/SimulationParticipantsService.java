package com.typecasters.apitowerofwords.Service;

import com.typecasters.apitowerofwords.Entity.SimulationParticipantsEntity;
import com.typecasters.apitowerofwords.Repository.SimulationParticipantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SimulationParticipantsService {
    @Autowired
    SimulationParticipantsRepository simulationParticipantsRepository;

    public SimulationParticipantsEntity addParticipant(SimulationParticipantsEntity participant) {
        return simulationParticipantsRepository.save(participant);
    }

    public List<SimulationParticipantsEntity> getAllParticipants() {
        return simulationParticipantsRepository.findAll();
    }

    public Optional<SimulationParticipantsEntity> getParticipantById(int id) {
        return simulationParticipantsRepository.findById(id);
    }

    public SimulationParticipantsEntity updateParticipant(SimulationParticipantsEntity participant) {
        return simulationParticipantsRepository.save(participant);
    }

    public void deleteParticipant(int id) {
        simulationParticipantsRepository.deleteById(id);
    }

}
