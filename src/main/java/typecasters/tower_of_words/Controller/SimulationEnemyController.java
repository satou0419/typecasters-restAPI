package typecasters.tower_of_words.Controller;

import typecasters.tower_of_words.Entity.SimulationEnemyEntity;
import typecasters.tower_of_words.Response.NoDataResponse;
import typecasters.tower_of_words.Response.Response;
import typecasters.tower_of_words.Service.SimulationEnemyService;
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
    public ResponseEntity<Object> insertSimulationWord(@RequestBody SimulationEnemyEntity word) {
        try {
            SimulationEnemyEntity insertedWord = simulationEnemyService.insertSimulationWord(word);
            return Response.response(HttpStatus.OK, "Simulation word inserted successfully", insertedWord);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/view")
    public ResponseEntity<Object> getAllSimulationWords() {
        try {
            List<SimulationEnemyEntity> words = simulationEnemyService.getAllSimulationWords();
            return Response.response(HttpStatus.OK, "Simulations words retrieved successfully", words);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/view_by_id/{id}")
    public ResponseEntity<Object> getSimulationWordById(@PathVariable int id) {
        try {
            Optional<SimulationEnemyEntity> word = simulationEnemyService.getSimulationWordById(id);
            return word.map(value -> Response.response(HttpStatus.OK, "Simulation word retrieved successfully", value))
                    .orElseGet(() -> NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Simulation word not found"));
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<Object> updateSimulationWord(@RequestBody SimulationEnemyEntity word) {
        try {
            SimulationEnemyEntity updatedWord = simulationEnemyService.updateSimulationWord(word);
            return Response.response(HttpStatus.OK, "Simulation word updated successfully", updatedWord);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Object> deleteSimulationWord(@PathVariable int id) {
        try {
            simulationEnemyService.deleteSimulationWord(id);
            return NoDataResponse.noDataResponse(HttpStatus.OK, "Simulation word deleted successfully");
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
}
