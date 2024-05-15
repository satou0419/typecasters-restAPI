package com.typecasters.apitowerofwords.Controller;

import com.typecasters.apitowerofwords.Service.SimulationService;
import com.typecasters.apitowerofwords.Entity.SimulationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/simulation")
public class SimulationController {
    @Autowired
    SimulationService simulationService;

    //CREATE
    @PostMapping("/insert")
    public ResponseEntity<String> insertSimulation(@RequestBody SimulationEntity simulation) {
         try {
            SimulationEntity insertedSimulation = simulationService.insertSimulation(simulation);
             return ResponseEntity.ok("Simulation Added!");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        }
    }

    //READ
    @GetMapping("/view_by_room/{roomID}")
    public ResponseEntity<List<SimulationEntity>> viewSimulationsByRoomID(@PathVariable int roomID) {
        List<SimulationEntity> simulations = simulationService.viewSimulationsByRoomID(roomID);
        return new ResponseEntity<>(simulations, HttpStatus.OK);
    }

    @GetMapping("/view_by_id/{simulationID}")
    public ResponseEntity<SimulationEntity> viewSimulationByID(@PathVariable int simulationID) {
        Optional<SimulationEntity> simulation = simulationService.viewSimulationByID(simulationID);
        return simulation.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/view_by_member/{userID}")
    public ResponseEntity<List<SimulationEntity>> viewSimulationsByMember(@PathVariable int userID) {
        List<SimulationEntity> simulations = simulationService.viewSimulationsByMember(userID);
        return new ResponseEntity<>(simulations, HttpStatus.OK);
    }

    //UPDATE
    @PutMapping("/edit")
    public ResponseEntity<String> editSimulation(@RequestBody SimulationEntity simulation) {
        try {
            SimulationEntity editedSimulation = simulationService.editSimulation(simulation);
            return ResponseEntity.ok("Simulation Updated!");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        }
    }

    //DELETE
    @PutMapping("/remove/{simulationID}")
    public ResponseEntity<String> removeSimulation(@PathVariable int simulationID) {
        try {
            simulationService.removeSimulation(simulationID);
            return ResponseEntity.ok("Simulation Removed!");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        }
    }
}
