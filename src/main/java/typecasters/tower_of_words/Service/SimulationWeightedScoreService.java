package typecasters.tower_of_words.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import typecasters.tower_of_words.Entity.SimulationParticipantsEntity;
import typecasters.tower_of_words.Entity.SimulationWeightedScoreEntity;
import typecasters.tower_of_words.Entity.SimulationWordsEntity;
import typecasters.tower_of_words.Repository.SimulationRepository;
import typecasters.tower_of_words.Repository.SimulationWeightedScoreRepository;
import typecasters.tower_of_words.Repository.SimulationWordsRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SimulationWeightedScoreService {

    @Autowired
    SimulationWeightedScoreRepository simulationWeightedScoreRepository;

    @Autowired
    SimulationRepository simulationRepository;

    @Autowired
    SimulationWordsRepository simulationWordsRepository;

    @Autowired
    SimulationParticipantsService simulationParticipantsService;

    public SimulationWeightedScoreEntity insertSimulationWeightedScore(
            SimulationWeightedScoreEntity simulationWeightedScore,
            int simulationID,
            int simulationParticipantID,
            int wordID)
    {
        SimulationParticipantsEntity simulationParticipants = simulationParticipantsService.getOneBySimulationParticipantsIDAndSimulationID(simulationParticipantID, simulationID)
                .orElseThrow(() -> new NoSuchElementException("Simulation Participants ID# " + simulationParticipantID + " does not exist!"));

        SimulationWordsEntity simulationWords = simulationWordsRepository.findById(wordID)
                .orElseThrow(() -> new NoSuchElementException("Simulation Word ID# " + wordID + " does not exist!"));

        simulationWeightedScore.setSimulationID(simulationParticipants.getSimulationID().getSimulationID());
        simulationWeightedScore.setSimulationParticipantID(simulationParticipants.getSimulationParticipantsID());
        simulationWeightedScore.setWordID(simulationWords.getSimulationWordsID());

        return simulationWeightedScoreRepository.save(simulationWeightedScore);
    }

    public List<SimulationWeightedScoreEntity> getAllSimulationWeightedScore(){
        return simulationWeightedScoreRepository.findAll();
    }

    public Optional<SimulationWeightedScoreEntity> getSimulationWeightedScoreByID(int simulationWeightedScoreID){
        return simulationWeightedScoreRepository.findById(simulationWeightedScoreID);
    }

    public Optional<SimulationWeightedScoreEntity> getSimulationWeightedScoreBySimulationIDAndSimulationParticipantIDAndWordID(
            int simulationID,
            int simulationParticipantID,
            int wordID)
    {
        SimulationParticipantsEntity simulationParticipants = simulationParticipantsService.getOneBySimulationParticipantsIDAndSimulationID(simulationParticipantID, simulationID)
                .orElseThrow(() -> new NoSuchElementException("Simulation Participants ID# " + simulationParticipantID + " does not exist!"));

        SimulationWordsEntity simulationWords = simulationWordsRepository.findById(wordID)
                .orElseThrow(() -> new NoSuchElementException("Simulation Word ID# " + wordID + " does not exist!"));

        return simulationWeightedScoreRepository.findOneBySimulationIDAndSimulationParticipantIDAndWordID(
                simulationParticipants.getSimulationID().getSimulationID(),
                simulationParticipants.getSimulationParticipantsID(),
                simulationWords.getSimulationWordsID());
    }

    public List<SimulationWeightedScoreEntity> getAllSimulationWeightedScoreBySimulationIDAndSimulationParticipantID(
            int simulationID,
            int simulationParticipantID)
    {
        SimulationParticipantsEntity simulationParticipants = simulationParticipantsService.getOneBySimulationParticipantsIDAndSimulationID(simulationParticipantID, simulationID)
                .orElseThrow(() -> new NoSuchElementException("Simulation Participants ID# " + simulationParticipantID + " does not exist!"));

        return simulationWeightedScoreRepository.findAllBySimulationIDAndSimulationParticipantID(
                simulationParticipants.getSimulationID().getSimulationID(),
                simulationParticipants.getSimulationParticipantsID());
    }

    public SimulationWeightedScoreEntity updateSimulationWeightedScoreBySimulationIDAndSimulationParticipantIDAndWordID(
            SimulationWeightedScoreEntity simulationWeightedScore,
            int simulationID,
            int simulationParticipantID,
            int wordID)
    {
        SimulationWeightedScoreEntity existingSimulationWeightedScore = getSimulationWeightedScoreBySimulationIDAndSimulationParticipantIDAndWordID(simulationID, simulationParticipantID, wordID)
                .orElseThrow(() -> new NoSuchElementException("Simulation Weighted Score doesn't exist!"));

        existingSimulationWeightedScore.setWeightedScore(simulationWeightedScore.getWeightedScore());

        return simulationWeightedScoreRepository.save(existingSimulationWeightedScore);
    }

    public void deleteSimulationWeightedScore(int simulationWeightedScoreID){
        if(getSimulationWeightedScoreByID(simulationWeightedScoreID).isPresent()){
            simulationWeightedScoreRepository.deleteById(simulationWeightedScoreID);
        }else{
            throw new NoSuchElementException("Simulation Weighted Score ID# " + simulationWeightedScoreID + " doesn't exist!");
        }
    }
}
