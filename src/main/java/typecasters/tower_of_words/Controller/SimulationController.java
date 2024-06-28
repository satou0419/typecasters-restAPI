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
    @PostMapping("/insert")
    public ResponseEntity<Object> insertSimulation(@RequestBody SimulationEntity simulation) {
        try {
            SimulationEntity insertedSimulation = simulationService.insertSimulation(simulation);
            return Response.response(HttpStatus.OK, "Simulation inserted successfully", insertedSimulation);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    //READ
    @GetMapping("/view_by_room/{roomID}")
    public ResponseEntity<Object> viewSimulationsByRoomID(@PathVariable int roomID) {
        try {
            List<SimulationEntity> simulations = simulationService.viewSimulationsByRoomID(roomID);
            return Response.response(HttpStatus.OK, "Simulations retrieved successfully", simulations);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/view_by_id/{simulationID}")
    public ResponseEntity<Object> viewSimulationByID(@PathVariable int simulationID) {
        try {
            Optional<SimulationEntity> simulation = simulationService.viewSimulationByID(simulationID);
            return simulation.map(value -> Response.response(HttpStatus.OK, "Simulation retrieved successfully", value))
                    .orElseGet(() -> NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Simulation not found"));
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/view_by_member/{userID}")
    public ResponseEntity<Object> viewSimulationsByMember(@PathVariable int userID) {
        try {
            List<SimulationEntity> simulations = simulationService.viewSimulationsByMember(userID);
            return Response.response(HttpStatus.OK, "Simulations retrieved successfully", simulations);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    //UPDATE
    @PutMapping("/edit")
    public ResponseEntity<Object> editSimulation(@RequestBody SimulationEntity simulation) {
        try {
            SimulationEntity editedSimulation = simulationService.editSimulation(simulation);
            return Response.response(HttpStatus.OK, "Simulation updated successfully", editedSimulation);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    //DELETE
    @PutMapping("/remove/{simulationID}")
    public ResponseEntity<Object> removeSimulation(@PathVariable int simulationID) {
        try {
            simulationService.removeSimulation(simulationID);
            return NoDataResponse.noDataResponse(HttpStatus.OK, "Simulation Removed!");
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
}
