package com.typecasters.apitowerofwords.Service;

import com.typecasters.apitowerofwords.Entity.RoomEntity;
import com.typecasters.apitowerofwords.Entity.SimulationEnemyEntity;
import com.typecasters.apitowerofwords.Entity.SimulationEntity;
import com.typecasters.apitowerofwords.Entity.UserEntity;
import com.typecasters.apitowerofwords.Repository.RoomRepository;
import com.typecasters.apitowerofwords.Repository.SimulationRepository;
import com.typecasters.apitowerofwords.Repository.UserRepository;
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
            simulation.getParticipants().addAll(room.get().getMembers());
            simulationRepository.save(simulation);
        }
        if (simulation.getWords().size() != 10) {
            throw new IllegalArgumentException("The number of words must be 10.");
        }
        return simulationRepository.save(simulation);
    }

    public SimulationEntity insertWord(SimulationEntity simulation, SimulationEnemyEntity word) {
        simulation.addWord(word);

        return simulation;
    }

    public List<SimulationEntity> viewSimulationsByRoomID(int roomID) {
        return simulationRepository.findByRoomID(roomID);
    }

    public Optional<SimulationEntity> viewSimulationByID(int simulationID) {
        return simulationRepository.findById(simulationID);
    }

    public List<SimulationEntity> viewSimulationsByMember(Integer userID) {
        List<SimulationEntity> simulationList = new ArrayList<>();
        try {
            Optional<UserEntity> user = userRepository.findById(userID);
            List<SimulationEntity> room = simulationRepository.findAll();

            for(SimulationEntity i : room) {
                for(Integer j : i.getParticipants()) {
                    if(j.equals(userID)) {
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

    public SimulationEntity removeSimulation(int simulationID) {
        SimulationEntity delete = new SimulationEntity();

        try {
            delete = simulationRepository.findById(simulationID).get();

            delete.setDeleted(true);
        }catch(NoSuchElementException ex) {
            throw new NoSuchElementException("Simulation " + simulationID + " does not exist!");
        }finally {
            return simulationRepository.save(delete);
        }
    }
}
