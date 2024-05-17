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
    public ResponseEntity<SimulationEntity> insertSimulation(@RequestBody SimulationEntity simulation) {
         try {
            SimulationEntity insertedSimulation = simulationService.insertSimulation(simulation);
             return new ResponseEntity<>(insertedSimulation, HttpStatus.OK);
         } catch (IllegalArgumentException ex) {
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         } catch (NoSuchElementException | NullPointerException ex){
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
    }

    //READ
    @GetMapping("/view_by_room/{roomID}")
    public ResponseEntity<List<SimulationEntity>> viewSimulationsByRoomID(@PathVariable int roomID) {
        try{
            List<SimulationEntity> simulations = simulationService.viewSimulationsByRoomID(roomID);
            return new ResponseEntity<>(simulations, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/view_by_id/{simulationID}")
    public ResponseEntity<SimulationEntity> viewSimulationByID(@PathVariable int simulationID) {
        try{
            Optional<SimulationEntity> simulation = simulationService.viewSimulationByID(simulationID);
            return simulation.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/view_by_member/{userID}")
    public ResponseEntity<List<SimulationEntity>> viewSimulationsByMember(@PathVariable int userID) {
        try{
            List<SimulationEntity> simulations = simulationService.viewSimulationsByMember(userID);
            return new ResponseEntity<>(simulations, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //UPDATE
    @PutMapping("/edit")
    public ResponseEntity<SimulationEntity> editSimulation(@RequestBody SimulationEntity simulation) {
        try {
            SimulationEntity editedSimulation = simulationService.editSimulation(simulation);
            return new ResponseEntity<>(editedSimulation, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
        } catch (NoSuchElementException | NullPointerException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
