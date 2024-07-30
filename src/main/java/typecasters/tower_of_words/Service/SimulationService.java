package typecasters.tower_of_words.Service;

import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import typecasters.tower_of_words.Entity.*;
import typecasters.tower_of_words.Repository.RoomRepository;
import typecasters.tower_of_words.Repository.SimulationAttemptsRepository;
import typecasters.tower_of_words.Repository.SimulationRepository;
import typecasters.tower_of_words.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Transactional
    public SimulationEntity createSimulation(SimulationEntity simulation) {
        Optional<RoomEntity> room = roomRepository.findById(simulation.getRoomID().getRoomID());
        if (room.isPresent()) {
            for (Integer i : room.get().getMembers()) {
                SimulationParticipantsEntity user = new SimulationParticipantsEntity();
                user.setUserID(i);
                user.setSimulationID(simulation);
                simulation.addParticipants(user);
            }
        }

        simulation = simulationRepository.save(simulation);

        // Generate SimulationWordAssessment
        List<SimulationWordAssessmentEntity> assessments = new ArrayList<>();
        for (SimulationEnemyEntity enemy : simulation.getEnemy()) {
            for (SimulationWordsEntity word : enemy.getWords()) {
                SimulationWordAssessmentEntity assessment = new SimulationWordAssessmentEntity();
                assessment.setSimulationID(simulation.getSimulationID());
                assessment.setSimulationEnemyID(enemy.getSimulationEnemyID());
                assessment.setSimulationWordID(word.getSimulationWordsID());
                assessment.setAccuracy(0);
                assessment.setAttempts(0);
                assessment.setScore(0);
                assessment.setDuration(0);
                assessments.add(assessment);
            }
        }
        simulationWordAssessmentService.addWordAssessments(assessments);

        // Generate SimulationAttempts
        List<SimulationAttemptsEntity> attemptsList = new ArrayList<>();
        for (SimulationParticipantsEntity participant : simulation.getParticipants()) {
            for (int i = 1; i <= simulation.getNumberOfAttempt(); i++) {
                SimulationAttemptsEntity attempt = new SimulationAttemptsEntity();
                attempt.setSimulationID(simulation.getSimulationID());
                attempt.setSimulationParticipantsID(participant);
                attempt.setCurrentAttempt(i);
                attemptsList.add(attempt);
            }
        }
        simulationAttemptsService.saveAllAttempts(attemptsList);

        // Generate StudentWordProgress
        List<StudentWordProgressEntity> progressList = new ArrayList<>();
        for (SimulationAttemptsEntity attempt : attemptsList) {
            for (SimulationWordAssessmentEntity assessment : assessments) {
                StudentWordProgressEntity progress = new StudentWordProgressEntity();
                progress.setSimulationAttemptsID(attempt);
                progress.setMistake(0);  // Default values
                progress.setCorrect(false); // Default values
                progress.setScore(0); // Default values
                progress.setDuration(0); // Default values
                progress.setAccuracy(0); // Default values
                progressList.add(progress);
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

    public String decrementAttempts(int simulationID){
        Optional<SimulationEntity> simulation = simulationRepository.findById(simulationID);

        if(simulation.isPresent()){
            SimulationEntity simulationObject = simulation.get();
            if(simulationObject.getNumberOfAttempt() > 0){
                simulationObject.setNumberOfAttempt(simulationObject.getNumberOfAttempt() - 1);

                simulationRepository.save(simulationObject);
                return "Number of attempts decremented!";
            }else{
                throw new IllegalArgumentException("You cannot decrement below 0");
            }

        }else{
            throw new NoSuchElementException("This simulation doesn't exist!");
        }

    }
}
