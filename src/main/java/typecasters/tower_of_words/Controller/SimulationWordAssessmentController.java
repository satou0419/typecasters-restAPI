package typecasters.tower_of_words.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import typecasters.tower_of_words.Entity.SimulationWordAssessmentEntity;
import typecasters.tower_of_words.Response.NoDataResponse;
import typecasters.tower_of_words.Response.Response;
import typecasters.tower_of_words.Service.SimulationWordAssessmentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/simulations_word_assessment")
public class SimulationWordAssessmentController {
    @Autowired
    SimulationWordAssessmentService simulationWordAssessmentService;

    @PostMapping("/insert")
    public ResponseEntity<Object> addParticipant(@RequestBody SimulationWordAssessmentEntity word) {
        try {
            SimulationWordAssessmentEntity insertWord = simulationWordAssessmentService.addWordAssessment(word);
            return Response.response(HttpStatus.OK, "Word assessment added successfully", insertWord);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/view")
    public ResponseEntity<Object> getAllWordAssessments() {
        try {
            List<SimulationWordAssessmentEntity> assessments = simulationWordAssessmentService.getAllWordAssessment();
            return Response.response(HttpStatus.OK, "Word assessments retrieved successfully", assessments);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/view/{wordAssessmentID}")
    public ResponseEntity<Object> getWordAssessmentById(@PathVariable int wordAssessmentID) {
        try {
            Optional<SimulationWordAssessmentEntity> assessment = simulationWordAssessmentService.getWordAssessmentById(wordAssessmentID);
            return assessment.map(value -> Response.response(HttpStatus.OK, "Word assessment retrieved successfully", value))
                    .orElseGet(() -> NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Word assessment not found"));
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/view_all_word_assessment_by/simulation/{simulationID}")
    public ResponseEntity<Object> getAllBySimulationID(@PathVariable int simulationID){
        try{
            List<SimulationWordAssessmentEntity> assessment = simulationWordAssessmentService.getAllBySimulationID(simulationID);
            return Response.response(HttpStatus.OK, "List of Word Assessment by Simulation ID " + simulationID + "!", assessment);
        }catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }catch (Exception e){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("/edit_by/simulation/{simulationID}/word/{wordID}")
    public ResponseEntity<Object> updateWordAssessment(@RequestBody SimulationWordAssessmentEntity word, @PathVariable int simulationID, @PathVariable int wordID) {
        try {
            SimulationWordAssessmentEntity updatedWord = simulationWordAssessmentService.setWordAssessment(word, simulationID, wordID);
            return Response.response(HttpStatus.OK, "Word assessment updated successfully", updatedWord);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @DeleteMapping("/remove/{wordAssessmentID}")
    public ResponseEntity<Object> deleteWordAssessment(@PathVariable int wordAssessmentID) {
        try {
            simulationWordAssessmentService.deleteWordAssessment(wordAssessmentID);
            return NoDataResponse.noDataResponse(HttpStatus.OK, "Word assessment deleted successfully");
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @PatchMapping("/update_assessment_average/{simulationID}")
    public ResponseEntity<Object> updateAssessmentAverage(@PathVariable int simulationID) {
        try {
            simulationWordAssessmentService.updateAssessmentAggregates(simulationID);
            return NoDataResponse.noDataResponse(HttpStatus.OK, "Assessment average updated successfully!");
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
