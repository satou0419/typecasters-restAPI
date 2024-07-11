package typecasters.tower_of_words.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import typecasters.tower_of_words.Entity.SimulationParticipantsEntity;
import typecasters.tower_of_words.Entity.SimulationWordAssessmentEntity;
import typecasters.tower_of_words.Service.SimulationWordAssessmentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/simulations_word_assessment")
public class SimulationWordAssessmentController {
    @Autowired
    SimulationWordAssessmentService simulationWordAssessmentService;

    @PostMapping("/insert")
    public ResponseEntity<SimulationWordAssessmentEntity> addParticipant (@RequestBody SimulationWordAssessmentEntity word) {
        SimulationWordAssessmentEntity insertWord = simulationWordAssessmentService.addWordAssessment(word);
        return new ResponseEntity<>(insertWord, HttpStatus.OK);
    }

    @GetMapping("/view")
    public ResponseEntity<List<SimulationWordAssessmentEntity>> getAllWordAssessments() {
        List<SimulationWordAssessmentEntity> assessments = simulationWordAssessmentService.getAllWordAssessment();
        return new ResponseEntity<>(assessments, HttpStatus.OK);
    }

    @GetMapping("/view/{wordAssessmentID}")
    public ResponseEntity<SimulationWordAssessmentEntity> getWordAssessmentById(@PathVariable int wordAssessmentID) {
        Optional<SimulationWordAssessmentEntity> assessment = simulationWordAssessmentService.getWordAssessmentById(wordAssessmentID);
        return assessment.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/edit")
    public ResponseEntity<SimulationWordAssessmentEntity> updateWordAssessment(@RequestBody SimulationWordAssessmentEntity word) {
        SimulationWordAssessmentEntity updatedWord = simulationWordAssessmentService.setWordAssessment(word);
        return new ResponseEntity<>(updatedWord, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{wordAssessmentID}")
    public ResponseEntity<Void> deleteWordAssessment(@PathVariable int wordAssessmentID) {
        simulationWordAssessmentService.deleteWordAssessment(wordAssessmentID);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
