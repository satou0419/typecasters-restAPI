package typecasters.tower_of_words.Service;

import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import typecasters.tower_of_words.Entity.*;
import typecasters.tower_of_words.Exception.SimulationNotAvailableException;
import typecasters.tower_of_words.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SimulationService {
    @Autowired
    SimulationRepository simulationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    SimulationAttemptsRepository simulationAttemptsRepository;

    @Autowired
    SimulationWordAssessmentService simulationWordAssessmentService;

    @Autowired
    @Lazy
    private SimulationAttemptsService simulationAttemptsService;

    @Autowired
    @Lazy
    private StudentWordProgressService studentWordProgressService;
    @Autowired
    private SimulationWordsRepository simulationWordsRepository;

    @Transactional
    public SimulationEntity createSimulation(SimulationEntity simulation) {
        Optional<RoomEntity> room = roomRepository.findById(simulation.getRoomID().getRoomID());
        LocalDateTime now = LocalDateTime.now();

        if (simulation.getDeadline() == null || !now.isBefore(simulation.getDeadline())) {
            throw new SimulationNotAvailableException("Simulation cannot be created before the deadline or if the deadline is null.");
        }

        if (room.isPresent()) {
           for (Integer i : room.get().getMembers()) {
               SimulationParticipantsEntity user = new SimulationParticipantsEntity(
               i, // userID
               0, // currentAttempts
               0, // mistakes
               0, // score
               0, // duration
               0, // accuracy
               false, // isDone
               simulation);

               simulation.addParticipants(user);
           }
        }

        simulation = simulationRepository.save(simulation);

        // Generate SimulationWordAssessment
        List<SimulationWordAssessmentEntity> assessments = new ArrayList<>();
        for (SimulationEnemyEntity enemy : simulation.getEnemy()) {
            for (Integer wordID : enemy.getWords()) {
                SimulationWordsEntity word = simulationWordsRepository.findById(wordID)
                        .orElseThrow(() -> new NoSuchElementException("Word " + wordID + " does not exist!"));

                assessments.add(new SimulationWordAssessmentEntity(
                        simulation.getSimulationID(),
                        enemy.getSimulationEnemyID(),
                        word.getSimulationWordsID(),
                        0,  // Accuracy
                        0,  // Attempts
                        0,  // Score
                        0   // Duration
                ));
            }
        }
        simulationWordAssessmentService.addWordAssessments(assessments);

        // Generate StudentWordProgress
        List<StudentWordProgressEntity> progressList = new ArrayList<>();
        for (SimulationWordAssessmentEntity assessment : assessments) {
            for (SimulationParticipantsEntity participant : simulation.getParticipants()) {
                progressList.add(new StudentWordProgressEntity(
                        assessment.getSimulationWordID(),
                        participant.getUserID(),
                        simulation.getSimulationID(),
                        0, // Mistake
                        false, // Correct
                        0, // Score
                        0, // Duration
                        0 // Accuracy
                ));
            }
        }
        studentWordProgressService.addProgress(progressList);

        return simulationRepository.save(simulation);
    }


    public Optional<SimulationEntity> findByID(int simulationID){
        return simulationRepository.findById(simulationID);
    }

    public SimulationEntity insertWord(SimulationEntity simulation, SimulationEnemyEntity word) {
        simulation.addWord(word);

        return simulation;
    }

    public List<SimulationEntity> roomSimulations(int roomID) {
        RoomEntity room = roomRepository.findById(roomID).get();
        return simulationRepository.findAllByRoomID(room);
    }

    public Optional<SimulationEntity> simulationDetails(int simulationID) {
        return simulationRepository.findById(simulationID);
    }

    public List<SimulationEntity> studentSimulations(Integer userID) {
        List<SimulationEntity> simulationList = new ArrayList<>();
        try {
            List<SimulationEntity> simulation = simulationRepository.findAll();

            for(SimulationEntity i : simulation) {
                for(SimulationParticipantsEntity j : i.getParticipants()) {
                    if(userID.equals(j.getUserID())) {
                        simulationList.add(i);
                    }
                }
            }

        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("User " + userID + " does not exist");
        }
        return simulationList;
    }

    public SimulationEntity renameSimulation(SimulationEntity simulation) {
        SimulationEntity edit = new SimulationEntity();

        try {
            edit = simulationRepository.findById(simulation.getSimulationID()).get();

            edit.setName(simulation.getName());

        }catch(NoSuchElementException ex) {
            throw new NoSuchElementException ("Simulation " + simulation.getSimulationID() + " does not exist");
        }finally {
            return simulationRepository.save(edit);
        }
    }

    public SimulationEntity editDeadlineSimulation(SimulationEntity simulation) {
        SimulationEntity edit = new SimulationEntity();

        try {
            edit = simulationRepository.findById(simulation.getSimulationID()).get();

            edit.setName(simulation.getName());
            edit.setDeadline(simulation.getDeadline());

        }catch(NoSuchElementException ex) {
            throw new NoSuchElementException ("Simulation " + simulation.getSimulationID() + " does not exist");
        }finally {
            return simulationRepository.save(edit);
        }
    }

    public String removeSimulation(int simulationID) {
        String msg = "";

        if(simulationRepository.findById(simulationID).isPresent()) {
            simulationRepository.deleteById(simulationID);

            msg = "Item " + simulationID + " is successfully deleted!";
        }

        return msg;
    }

}
