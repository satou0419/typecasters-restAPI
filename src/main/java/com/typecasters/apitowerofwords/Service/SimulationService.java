package com.typecasters.apitowerofwords.Service;

import com.typecasters.apitowerofwords.Entity.*;
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
            for(Integer i : room.get().getMembers()){
                SimulationParticipantsEntity user = new SimulationParticipantsEntity();
                user.setUserID(i.intValue());
                simulation.addParticipants(user);
            }
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
