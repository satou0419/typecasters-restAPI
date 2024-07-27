package typecasters.tower_of_words.Service;

import typecasters.tower_of_words.Entity.SimulationEntity;
import typecasters.tower_of_words.Entity.SimulationParticipantsEntity;
import typecasters.tower_of_words.Entity.StudentWordProgressEntity;
import typecasters.tower_of_words.Repository.SimulationParticipantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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
        SimulationParticipantsEntity edit = new SimulationParticipantsEntity();
        try {
            edit = simulationParticipantsRepository.findById(participant.getSimulationParticipantsID()).get();

            edit.setDuration(participant.getDuration());
            edit.setScore(participant.getScore());
            edit.setDone(participant.isDone());

        }catch(NoSuchElementException ex) {
            throw new NoSuchElementException ("Simulation " + participant.getSimulationParticipantsID() + " does not exist");
        }

        return simulationParticipantsRepository.save(edit);
    }

    public void deleteParticipant(int id) {
        simulationParticipantsRepository.deleteById(id);
    }

}
