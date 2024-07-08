package typecasters.tower_of_words.Controller;

import typecasters.tower_of_words.Service.SimulationService;
import typecasters.tower_of_words.Entity.SimulationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/simulation")
public class SimulationController {
    @Autowired
    SimulationService simulationService;

    //CREATE
    @PostMapping("/create_simulation")
    public ResponseEntity<SimulationEntity> createSimulation(@RequestBody SimulationEntity simulation) {
         try {
            SimulationEntity createdSimulation = simulationService.createSimulation(simulation);
             return new ResponseEntity<>(createdSimulation, HttpStatus.OK);
         } catch (IllegalArgumentException ex) {
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         } catch (NoSuchElementException | NullPointerException ex){
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
    }

    @PostMapping("/clone_simulation")
    public ResponseEntity<SimulationEntity> cloneSimulation(@RequestBody SimulationEntity simulation) {
        try {
            SimulationEntity createdSimulation = simulationService.createSimulation(simulation);
            return new ResponseEntity<>(createdSimulation, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //READ
    @GetMapping("/room_simulations/{roomID}")
    public ResponseEntity<List<SimulationEntity>> roomSimulations(@PathVariable int roomID) {
        List<SimulationEntity> simulations = simulationService.roomSimulations(roomID);
        return new ResponseEntity<>(simulations, HttpStatus.OK);

    }

    @GetMapping("/simulation_details/{simulationID}")
    public ResponseEntity<SimulationEntity> simulationDetails(@PathVariable int simulationID) {
        SimulationEntity simulation = simulationService.simulationDetails(simulationID).get();
        return new ResponseEntity<>(simulation, HttpStatus.OK);
    }

    @GetMapping("/student_simulations/{userID}")
    public ResponseEntity<List<SimulationEntity>> studentSimulations(@PathVariable int userID) {
        try{
            List<SimulationEntity> simulations = simulationService.studentSimulations(userID);
            return new ResponseEntity<>(simulations, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //UPDATE
    @PutMapping("/rename")
    public ResponseEntity<SimulationEntity> renameSimulation(@RequestBody SimulationEntity simulation) {
        try {
            SimulationEntity editedSimulation = simulationService.renameSimulation(simulation);
            return new ResponseEntity<>(editedSimulation, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/edit_deadline")
    public ResponseEntity<SimulationEntity> editDeadlineSimulation(@RequestBody SimulationEntity simulation) {
        try {
            SimulationEntity editedSimulation = simulationService.editDeadlineSimulation(simulation);
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
