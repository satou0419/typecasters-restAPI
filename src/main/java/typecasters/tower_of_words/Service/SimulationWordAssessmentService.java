package typecasters.tower_of_words.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import typecasters.tower_of_words.Entity.SimulationWordAssessmentEntity;
import typecasters.tower_of_words.Repository.SimulationWordAssessmentRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SimulationWordAssessmentService {
    @Autowired
    SimulationWordAssessmentRepository simulationWordAssessmentRepository;

    public SimulationWordAssessmentEntity addWordAssessment(SimulationWordAssessmentEntity word) {
        return simulationWordAssessmentRepository.save(word);
    }

    public List<SimulationWordAssessmentEntity> getAllWordAssessment() {
        return simulationWordAssessmentRepository.findAll();
    }

    public Optional<SimulationWordAssessmentEntity> getWordAssessmentById(int id) {
        return simulationWordAssessmentRepository.findById(id);
    }

    @Transactional
    public SimulationWordAssessmentEntity setWordAssessment(SimulationWordAssessmentEntity word) {
        SimulationWordAssessmentEntity edit = new SimulationWordAssessmentEntity();
        try {
            edit = simulationWordAssessmentRepository.findById(word.getSimulationWordAssessmentID()).get();

            edit.setAccuracy(word.getAccuracy());
            edit.setAttempts(word.getAttempts());
            edit.setDuration(word.getDuration());
            edit.setScore(word.getScore());

        }catch(NoSuchElementException ex) {
            throw new NoSuchElementException ("Word Assessment " + word.getSimulationWordAssessmentID() + " does not exist");
        }

        return simulationWordAssessmentRepository.save(edit);
    }

    public List<SimulationWordAssessmentEntity> addWordAssessments(List<SimulationWordAssessmentEntity> words) {
        return simulationWordAssessmentRepository.saveAll(words);
    }

    public void deleteWordAssessment(int id) {
        simulationWordAssessmentRepository.deleteById(id);
    }
}
