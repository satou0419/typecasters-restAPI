package typecasters.tower_of_words.Service;

import typecasters.tower_of_words.Entity.*;
import typecasters.tower_of_words.Repository.SimulationParticipantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import typecasters.tower_of_words.Repository.SimulationRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SimulationParticipantsService {
    @Autowired
    SimulationParticipantsRepository simulationParticipantsRepository;

    @Autowired
    SimulationRepository simulationRepository;

    public SimulationParticipantsEntity addParticipant(SimulationParticipantsEntity participant) {
        return simulationParticipantsRepository.save(participant);
    }

    public List<SimulationParticipantsEntity> getAllParticipants() {
        return simulationParticipantsRepository.findAll();
    }

    public Optional<SimulationParticipantsEntity> getParticipantById(int id) {
        return simulationParticipantsRepository.findById(id);
    }

    public Optional<Integer> getSimulationParticipantsIDIDByUserIDAndSimulationID(Integer userID, Integer simulation) {
        SimulationEntity simulationObject = simulationRepository.findById(simulation)
                .orElseThrow(() -> new NoSuchElementException("Simulation " + simulation + " doesn't exist!"));

        return simulationParticipantsRepository.findSimulationParticipantsIDByUserIDAndSimulationID(userID, simulationObject);
    }

    public Optional<SimulationParticipantsEntity> getOneBySimulationParticipantsIDAndSimulationID(int simulationParticipantsID, int simulation){

        SimulationEntity simulationObject = simulationRepository.findById(simulation)
                .orElseThrow(() -> new NoSuchElementException("Simulation " + simulation + " doesn't exist!"));

        return simulationParticipantsRepository.findOneBySimulationParticipantsIDAndSimulationID(simulationParticipantsID, simulationObject);
    }


    public SimulationParticipantsEntity updateParticipant(SimulationParticipantsEntity participant, int simulationID) {
        SimulationParticipantsEntity simulationAttempt = getOneBySimulationParticipantsIDAndSimulationID(participant.getSimulationParticipantsID(), simulationID)
                .orElseThrow(() -> new NoSuchElementException("Simulation doesn't exist!"));

        simulationAttempt.setDuration(participant.getDuration());
        simulationAttempt.setScore(participant.getScore());
        simulationAttempt.setAccuracy(participant.getScore());

        return simulationParticipantsRepository.save(simulationAttempt);
    }

    public void deleteParticipant(int id) {
        simulationParticipantsRepository.deleteById(id);
    }

}
