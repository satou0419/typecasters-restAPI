package com.typecasters.apitowerofwords.Service;

import com.typecasters.apitowerofwords.Entity.SimulationEnemyEntity;
import com.typecasters.apitowerofwords.Repository.SimulationEnemyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SimulationEnemyService {
    @Autowired
    private SimulationEnemyRepository simulationEnemyRepository;

    public SimulationEnemyEntity insertSimulationWord(SimulationEnemyEntity word) {
        return simulationEnemyRepository.save(word);
    }

    public List<SimulationEnemyEntity> getAllSimulationWords() {
        return simulationEnemyRepository.findAll();
    }

    public Optional<SimulationEnemyEntity> getSimulationWordById(int id) {
        return simulationEnemyRepository.findById(id);
    }

    public SimulationEnemyEntity updateSimulationWord(SimulationEnemyEntity word) {
        return simulationEnemyRepository.save(word);
    }

    public void deleteSimulationWord(int id) {
        simulationEnemyRepository.deleteById(id);
    }
}
