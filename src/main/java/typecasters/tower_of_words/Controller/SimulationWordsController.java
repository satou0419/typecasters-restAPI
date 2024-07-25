package typecasters.tower_of_words.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import typecasters.tower_of_words.Entity.SimulationWordsEntity;
import typecasters.tower_of_words.Response.NoDataResponse;
import typecasters.tower_of_words.Response.Response;
import typecasters.tower_of_words.Service.SimulationWordsService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/simulation_words")
public class SimulationWordsController {
    @Autowired
    private SimulationWordsService simulationWordsService;

    @PostMapping("/insert")
    public ResponseEntity<Object> addWord(@RequestBody SimulationWordsEntity word) {
        try {
            SimulationWordsEntity insertedWord = simulationWordsService.addSimulationWord(word);
            return Response.response(HttpStatus.OK, "Word added successfully", insertedWord);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/view")
    public ResponseEntity<Object> getAllWords() {
        try {
            List<SimulationWordsEntity> words = simulationWordsService.getAllSimulationWord();
            return Response.response(HttpStatus.OK, "Words retrieved successfully", words);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/get_word_by/{simulationWordsID}")
    public ResponseEntity<Object> getWordBySimulationWordsID(@PathVariable int simulationWordsID){
        try{
            Optional<String> word = simulationWordsService.getWordBySimulationWordsID(simulationWordsID);
            if(word.isPresent()){
                String wordObject = word.get();
                return Response.response(HttpStatus.OK, "Word retrieved succesfully", wordObject);
            }else {
                return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Word does not exist for this id = " + simulationWordsID);
            }
        }catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }catch (Exception ex){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @GetMapping("/view/{wordID}")
    public ResponseEntity<Object> getWordById(@PathVariable int wordID) {
        try {
            Optional<SimulationWordsEntity> word = simulationWordsService.getSimulationWordById(wordID);
            return word.map(value -> Response.response(HttpStatus.OK, "Word retrieved successfully", value))
                    .orElseGet(() -> NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Word not found"));
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/word_bank/{userID}")
    public ResponseEntity<Object> getSimulationWordsByCreatorID(@PathVariable int userID) {
        try {
            List<SimulationWordsEntity> words = simulationWordsService.getSimulationWordsByCreatorID(userID);
            return Response.response(HttpStatus.OK, "Simulation words retrieved successfully", words);
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<Object> updateWord(@RequestBody SimulationWordsEntity word) {
        try {
            SimulationWordsEntity updatedWord = simulationWordsService.setIndex(word);
            return Response.response(HttpStatus.OK, "Word updated successfully", updatedWord);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @DeleteMapping("/remove/{wordID}")
    public ResponseEntity<Object> deleteWord(@PathVariable int wordID) {
        try {
            simulationWordsService.deleteSimulationWord(wordID);
            return NoDataResponse.noDataResponse(HttpStatus.OK, "Word deleted successfully");
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
}
