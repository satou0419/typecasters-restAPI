package typecasters.tower_of_words.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import typecasters.tower_of_words.Entity.SimulationAttemptsEntity;
import typecasters.tower_of_words.Entity.SimulationEntity;
import typecasters.tower_of_words.Entity.SimulationParticipantsEntity;
import typecasters.tower_of_words.Repository.SimulationAttemptsRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SimulationAttemptsService {

    private final SimulationAttemptsRepository simulationAttemptsRepository;
    private final SimulationService simulationService;
    private final SimulationParticipantsService simulationParticipantsService;

    @Autowired
    public SimulationAttemptsService(SimulationAttemptsRepository simulationAttemptsRepository,
                                     SimulationService simulationService,
                                     SimulationParticipantsService simulationParticipantsService) {
        this.simulationAttemptsRepository = simulationAttemptsRepository;
        this.simulationService = simulationService;
        this.simulationParticipantsService = simulationParticipantsService;
    }

    public SimulationAttemptsEntity insertNewAttempt(SimulationAttemptsEntity newAttempt, int simulationID, int simulationParticipantsID) {
        Optional<SimulationEntity> simulation = simulationService.findByID(simulationID);
        Optional<SimulationParticipantsEntity> participant = simulationParticipantsService.getParticipantById(simulationParticipantsID);

        if (simulation.isPresent() && participant.isPresent()) {
            newAttempt.setSimulationID(simulationID);
            newAttempt.setSimulationParticipantsID(simulationParticipantsID);
            return simulationAttemptsRepository.save(newAttempt);
        } else {
            throw new NoSuchElementException("Simulation or participant does not exist!");
        }
    }

    public List<SimulationAttemptsEntity> getAllAttempts(){
        return simulationAttemptsRepository.findAll();
    }

    public Optional<SimulationAttemptsEntity> getAttemptByID(int simulationAttemptsID){
        return simulationAttemptsRepository.findById(simulationAttemptsID);
    }

    public Optional<SimulationAttemptsEntity> findOneBySimulationIDAndSimulationParticipantsID(
            int simulationID,
            int simulationParticipantsID,
            int currentAttempts){

        SimulationEntity simulation = simulationService.findByID(simulationID)
                .orElseThrow(() -> new NoSuchElementException("The Simulation doesn't exist!"));

        SimulationParticipantsEntity simulationParticipant = simulationParticipantsService.getParticipantById(simulationParticipantsID)
                .orElseThrow(() -> new NoSuchElementException("Simulation Participant doesn't exist!"));

        return simulationAttemptsRepository.findOneBySimulationIDAndSimulationParticipantsID(
                simulationID,
                simulationParticipantsID,
                currentAttempts);
    }

    @Transactional
    public SimulationAttemptsEntity updateAttemptByID(int simulationAttemptsID, SimulationAttemptsEntity attempts) {
        SimulationAttemptsEntity existingAttempt = simulationAttemptsRepository.findById(simulationAttemptsID)
                .orElseThrow(() -> new NoSuchElementException("Current Attempt " + simulationAttemptsID + " does not exist!"));

        existingAttempt.setCurrentAccuracy(attempts.getCurrentAccuracy());
        existingAttempt.setCurrentDuration(attempts.getCurrentDuration());
        existingAttempt.setCurrentMistakes(attempts.getCurrentMistakes());
        existingAttempt.setCurrentScore(attempts.getCurrentScore());
        existingAttempt.setDone(attempts.isDone());

        return simulationAttemptsRepository.save(existingAttempt);
    }

    @Transactional
    public SimulationAttemptsEntity updateAttemptBySimulationIDAndSimulationParticipantsID(
            int simulationID,
            int simulationParticipantsID,
            int currentAttempts,
            SimulationAttemptsEntity simulationAttemptsEdit) {

        SimulationAttemptsEntity existingAttempt = findOneBySimulationIDAndSimulationParticipantsID(simulationID, simulationParticipantsID, currentAttempts)
                .orElseThrow(() -> new NoSuchElementException("Simulation Attempt doesn't exist!"));

        return updateAttemptByID(existingAttempt.getSimulationAttemptsID(), simulationAttemptsEdit);

    }


    public String deleteAttempt(int simulationAttemptsID){
        if (simulationAttemptsRepository.existsById(simulationAttemptsID)) {
            simulationAttemptsRepository.deleteById(simulationAttemptsID);
            return "Simulation Attempt ID " + simulationAttemptsID + " has been deleted!";
        } else {
            throw new NoSuchElementException("Simulation Attempt ID " + simulationAttemptsID + " does not exist!");
        }
    }
}

