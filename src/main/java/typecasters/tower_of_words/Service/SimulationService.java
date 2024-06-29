package typecasters.tower_of_words.Service;

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


    public SimulationEntity insertSimulation(SimulationEntity simulation) {
        Optional<RoomEntity> room = roomRepository.findById(simulation.getRoomID());
        if (room.isPresent()) {
            for(Integer i : room.get().getMembers()){
                SimulationParticipantsEntity user = new SimulationParticipantsEntity();
                user.setUserID(i.intValue());
                simulation.addParticipants(user);
            }
        }
        return simulationRepository.save(simulation);
    }

    public SimulationEntity insertWord(SimulationEntity simulation, SimulationEnemyEntity word) {
        simulation.addWord(word);

        return simulation;
    }

    public List<SimulationEntity> viewSimulationsByRoomID(int roomID) {
        return simulationRepository.findAllByRoomID(roomID);
    }

    public Optional<SimulationEntity> viewSimulationByID(int simulationID) {
        return simulationRepository.findById(simulationID);
    }

    public List<SimulationEntity> viewSimulationsByMember(Integer userID) {
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

    public SimulationEntity editSimulation(SimulationEntity simulation) {
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
