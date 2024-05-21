package com.typecasters.apitowerofwords.Controller;

import com.typecasters.apitowerofwords.Entity.SimulationEnemyEntity;
import com.typecasters.apitowerofwords.Service.SimulationEnemyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/simulation_enemy")
public class SimulationEnemyController {
    @Autowired
    private SimulationEnemyService simulationEnemyService;

    @PostMapping("/insert")
    public ResponseEntity<SimulationEnemyEntity> insertSimulationWord(@RequestBody SimulationEnemyEntity word) {
        SimulationEnemyEntity insertedWord = simulationEnemyService.insertSimulationWord(word);
        return new ResponseEntity<>(insertedWord, HttpStatus.OK);
    }

    @GetMapping("/view")
    public ResponseEntity<List<SimulationEnemyEntity>> getAllSimulationWords() {
        List<SimulationEnemyEntity> words = simulationEnemyService.getAllSimulationWords();
        return new ResponseEntity<>(words, HttpStatus.OK);
    }

    @GetMapping("view_by_id/{id}")
    public ResponseEntity<SimulationEnemyEntity> getSimulationWordById(@PathVariable int id) {
        Optional<SimulationEnemyEntity> word = simulationEnemyService.getSimulationWordById(id);
        return word.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/edit")
    public ResponseEntity<SimulationEnemyEntity> updateSimulationWord(@RequestBody SimulationEnemyEntity word) {
        SimulationEnemyEntity updatedWord = simulationEnemyService.updateSimulationWord(word);
        return new ResponseEntity<>(updatedWord, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Void> deleteSimulationWord(@PathVariable int id) {
        simulationEnemyService.deleteSimulationWord(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
