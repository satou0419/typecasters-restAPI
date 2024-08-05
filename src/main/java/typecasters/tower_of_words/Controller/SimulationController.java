package typecasters.tower_of_words.Controller;

import typecasters.tower_of_words.Response.NoDataResponse;
import typecasters.tower_of_words.Response.Response;
import typecasters.tower_of_words.Service.SimulationService;
import typecasters.tower_of_words.Entity.SimulationEntity;
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
    @PostMapping("/create_simulation")
    public ResponseEntity<Object> createSimulation(@RequestBody SimulationEntity simulation) {
        try {
            SimulationEntity createdSimulation = simulationService.createSimulation(simulation);
            return Response.response(HttpStatus.OK, "Simulation created successfully", createdSimulation);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (Exception e){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/clone_simulation/simulation/{simulationID}/room/{roomID}")
    public ResponseEntity<Object> cloneSimulation(@PathVariable int simulationID, @PathVariable int roomID) {
        try {
            SimulationEntity createdSimulation = simulationService.cloneSimulation(simulationID, roomID);
            return Response.response(HttpStatus.OK, "Simulation cloned successfully!", createdSimulation);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    //READ
    @GetMapping("/room_simulations/{roomID}")
    public ResponseEntity<Object> roomSimulations(@PathVariable int roomID) {
        try{
            List<SimulationEntity> simulations = simulationService.roomSimulations(roomID);
            return Response.response(HttpStatus.OK, "Simulations retrieved successfully", simulations);
        }catch(Exception e){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @GetMapping("/simulation_details/{simulationID}")
    public ResponseEntity<Object> simulationDetails(@PathVariable int simulationID) {
        try {
            Optional<SimulationEntity> simulation = simulationService.simulationDetails(simulationID);
            return simulation.map(value -> Response.response(HttpStatus.OK, "Simulation details retrieved successfully", value))
                    .orElseGet(() -> NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Simulation not found"));
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/student_simulations/{userID}")
    public ResponseEntity<Object> studentSimulations(@PathVariable int userID) {
        try {
            List<SimulationEntity> simulations = simulationService.studentSimulations(userID);
            return Response.response(HttpStatus.OK, "Simulations retrieved successfully", simulations);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    //UPDATE
    @PutMapping("/rename")
    public ResponseEntity<Object> renameSimulation(@RequestBody SimulationEntity simulation) {
        try {
            SimulationEntity editedSimulation = simulationService.renameSimulation(simulation);
            return Response.response(HttpStatus.OK, "Simulation renamed successfully", editedSimulation);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PatchMapping("/edit_simulation/simulation/{simulationID}")
    public ResponseEntity<Object> editDeadlineSimulation(@RequestBody SimulationEntity simulation, @PathVariable int simulationID) {
        try {
            SimulationEntity editedSimulation = simulationService.editDeadlineSimulation(simulation, simulationID);
            return Response.response(HttpStatus.OK, "Simulation deadline edited successfully", editedSimulation);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    //DELETE
    @DeleteMapping("/remove/{simulationID}")
    public ResponseEntity<Object> removeSimulation(@PathVariable int simulationID) {
        try {
            simulationService.removeSimulation(simulationID);
            return NoDataResponse.noDataResponse(HttpStatus.OK, "Simulation removed successfully");
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

//    @GetMapping("/decrement_num_of_attempts_by/{simulationID}")
//    public ResponseEntity<Object> decrementAttempts(@PathVariable int simulationID){
//        try{
//            String attempt = simulationService.decrementAttempts(simulationID);
//            return NoDataResponse.noDataResponse(HttpStatus.OK, attempt);
//        }catch (NoSuchElementException ex){
//            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
//        }catch (IllegalArgumentException ex){
//            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
//        }catch (Exception ex){
//            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
//        }
//    }
}
