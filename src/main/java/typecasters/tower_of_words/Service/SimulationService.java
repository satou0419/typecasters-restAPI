package typecasters.tower_of_words.Service;

import jakarta.transaction.Transactional;
import typecasters.tower_of_words.Entity.*;
import typecasters.tower_of_words.Repository.RoomRepository;
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
    SimulationWordAssessmentService simulationWordAssessmentService;

    @Transactional
    public SimulationEntity createSimulation(SimulationEntity simulation) {

        Optional<RoomEntity> room = roomRepository.findById(simulation.getRoomID().getRoomID());
        if (room.isPresent()) {
            for (Integer i : room.get().getMembers()) {
                SimulationParticipantsEntity user = new SimulationParticipantsEntity();
                user.setUserID(i);
                simulation.addParticipants(user);
            }
        }

        simulation = simulationRepository.save(simulation);

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

        simulation.getAssessment().addAll(assessments);

        return simulationRepository.save(simulation);
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
