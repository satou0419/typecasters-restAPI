package typecasters.tower_of_words.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import typecasters.tower_of_words.Entity.SimulationWordsEntity;
import typecasters.tower_of_words.Repository.SimulationWordsRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SimulationWordsService {
    @Autowired
    SimulationWordsRepository simulationWordsRepository;

    public SimulationWordsEntity addSimulationWord(SimulationWordsEntity word) {

        Optional<SimulationWordsEntity> checkWordIfExist = simulationWordsRepository.findOneByCreatorIDAndWord(word.getCreatorID(), word.getWord());

        if(checkWordIfExist.isPresent()){
            throw new IllegalArgumentException("Word already exist!");
        }else{
            return simulationWordsRepository.save(word);
        }


    }

    public List<SimulationWordsEntity> getAllSimulationWord() {
        return simulationWordsRepository.findAll();
    }

    public Optional<SimulationWordsEntity> getSimulationWordById(int id) {
        return simulationWordsRepository.findById(id);
    }

    public List<SimulationWordsEntity> getSimulationWordsByCreatorID(int userID){
        return simulationWordsRepository.findAllByCreatorID(userID);
    }

    public Optional<String> getWordBySimulationWordsID(int simulationWordsID){
        return simulationWordsRepository.findWordBySimulationWordsID(simulationWordsID);
    }

    public SimulationWordsEntity setIndex(SimulationWordsEntity word) {
        SimulationWordsEntity edit = new SimulationWordsEntity();
        try {
            edit = simulationWordsRepository.findById(word.getSimulationWordsID()).get();

            edit.setSilentIndex(word.getSilentIndex());

        }catch(NoSuchElementException ex) {
            throw new NoSuchElementException ("Simulation Word " + word.getSimulationWordsID() + " does not exist");
        }

        return simulationWordsRepository.save(edit);
    }

    public void deleteSimulationWord(int id) {
        simulationWordsRepository.deleteById(id);
    }
}
