package typecasters.tower_of_words.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import typecasters.tower_of_words.Entity.SimulationWordsEntity;
import typecasters.tower_of_words.Service.SimulationWordsService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/simulation_words")
public class SimulationWordsController {
    @Autowired
    private SimulationWordsService simulationWordsService;

    @PostMapping("/insert")
    public ResponseEntity<SimulationWordsEntity> addWord(@RequestBody SimulationWordsEntity word) {
        SimulationWordsEntity insertedWord = simulationWordsService.addSimulationWord(word);
        return new ResponseEntity<>(insertedWord, HttpStatus.OK);
    }

    @GetMapping("/view")
    public ResponseEntity<List<SimulationWordsEntity>> getAllWords() {
        List<SimulationWordsEntity> words = simulationWordsService.getAllSimulationWord();
        return new ResponseEntity<>(words, HttpStatus.OK);
    }

    @GetMapping("/view/{wordID}")
    public ResponseEntity<SimulationWordsEntity> getWordById(@PathVariable int wordID) {
        Optional<SimulationWordsEntity> word = simulationWordsService.getSimulationWordById(wordID);
        return word.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/word_bank/{userID}")
    public ResponseEntity<List<SimulationWordsEntity>> getSimulationWordsByCreatorID(@PathVariable int userID) {
        List<SimulationWordsEntity> words = simulationWordsService.getSimulationWordsByCreatorID(userID);
        return new ResponseEntity<>(words, HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<SimulationWordsEntity> updateWord(@RequestBody SimulationWordsEntity word) {
        SimulationWordsEntity updatedWord = simulationWordsService.setIndex(word);
        return new ResponseEntity<>(updatedWord, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{wordID}")
    public ResponseEntity<Void> deleteWord(@PathVariable int wordID) {
        simulationWordsService.deleteSimulationWord(wordID);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
