package com.typecasters.apitowerofwords.Controller;

import com.typecasters.apitowerofwords.Entity.SimulationEnemyEntity;
import com.typecasters.apitowerofwords.Entity.SimulationParticipantsEntity;
import com.typecasters.apitowerofwords.Service.SimulationParticipantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/simulations_participants")
public class SimulationParticipantsController {
    @Autowired
    private SimulationParticipantsService simulationParticipantsService;

    @PostMapping("/insert")
    public ResponseEntity<SimulationParticipantsEntity> addParticipant (@RequestBody SimulationParticipantsEntity participant) {
        SimulationParticipantsEntity insertParticipant = simulationParticipantsService.addParticipant(participant);
        return new ResponseEntity<>(insertParticipant, HttpStatus.OK);
    }

    @GetMapping("/view")
    public ResponseEntity<List<SimulationParticipantsEntity>> getAllParticipants () {
        List<SimulationParticipantsEntity> participant = simulationParticipantsService.getAllParticipants();
        return new ResponseEntity<>(participant, HttpStatus.OK);
    }

    @GetMapping("/view_by_id/{simulationParticipantsID}")
    public ResponseEntity<SimulationParticipantsEntity> getSimulationParticipantsByID(@PathVariable int simulationParticipantsID) {
        Optional<SimulationParticipantsEntity> participant = simulationParticipantsService.getParticipantById(simulationParticipantsID);
        return participant.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/edit")
    public ResponseEntity<SimulationParticipantsEntity> updateSimulationParticipant(@RequestBody SimulationParticipantsEntity participant) {
        SimulationParticipantsEntity updatedParticipant = simulationParticipantsService.updateParticipant(participant);
        return new ResponseEntity<>(updatedParticipant, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{simulationParticipantsID}")
    public ResponseEntity<Void> deleteSimulationParticipant(@PathVariable int simulationParticipantsID) {
        simulationParticipantsService.deleteParticipant(simulationParticipantsID);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
