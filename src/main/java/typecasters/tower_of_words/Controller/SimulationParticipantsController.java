package typecasters.tower_of_words.Controller;

import typecasters.tower_of_words.Entity.SimulationParticipantsEntity;
import typecasters.tower_of_words.Response.NoDataResponse;
import typecasters.tower_of_words.Response.Response;
import typecasters.tower_of_words.Service.SimulationParticipantsService;
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
    public ResponseEntity<Object> addParticipant(@RequestBody SimulationParticipantsEntity participant) {
        try {
            SimulationParticipantsEntity insertParticipant = simulationParticipantsService.addParticipant(participant);
            return Response.response(HttpStatus.OK, "Participant added successfully", insertParticipant);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/view")
    public ResponseEntity<Object> getAllParticipants() {
        try {
            List<SimulationParticipantsEntity> participants = simulationParticipantsService.getAllParticipants();
            return Response.response(HttpStatus.OK, "Participants retrieved successfully", participants);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/view_by_id/{simulationParticipantsID}")
    public ResponseEntity<Object> getSimulationParticipantsByID(@PathVariable int simulationParticipantsID) {
        try {
            Optional<SimulationParticipantsEntity> participant = simulationParticipantsService.getParticipantById(simulationParticipantsID);
            return participant.map(value -> Response.response(HttpStatus.OK, "Participant retrieved successfully", value))
                    .orElseGet(() -> NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Participant not found"));
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<Object> updateSimulationParticipant(@RequestBody SimulationParticipantsEntity participant) {
        try {
            SimulationParticipantsEntity updatedParticipant = simulationParticipantsService.updateParticipant(participant);
            return Response.response(HttpStatus.OK, "Participant updated successfully", updatedParticipant);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @DeleteMapping("/remove/{simulationParticipantsID}")
    public ResponseEntity<Object> deleteSimulationParticipant(@PathVariable int simulationParticipantsID) {
        try {
            simulationParticipantsService.deleteParticipant(simulationParticipantsID);
            return NoDataResponse.noDataResponse(HttpStatus.OK, "Participant deleted successfully");
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
}
