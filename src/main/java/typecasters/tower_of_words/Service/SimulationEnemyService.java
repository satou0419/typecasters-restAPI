package typecasters.tower_of_words.Service;

import typecasters.tower_of_words.Entity.SimulationEnemyEntity;
import typecasters.tower_of_words.Repository.SimulationEnemyRepository;
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
