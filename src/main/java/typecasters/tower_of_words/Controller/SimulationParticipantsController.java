package typecasters.tower_of_words.Controller;

import typecasters.tower_of_words.Entity.SimulationEntity;
import typecasters.tower_of_words.Entity.SimulationParticipantsEntity;
import typecasters.tower_of_words.Entity.StudentWordProgressEntity;
import typecasters.tower_of_words.Response.NoDataResponse;
import typecasters.tower_of_words.Response.Response;
import typecasters.tower_of_words.Service.SimulationParticipantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
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

    @GetMapping("/get_simulation_participants_id_by/{userID}/{simulation}")
    public ResponseEntity<Object> getSimulationParticipantsIDIDByUserIDAndSimulationID(@PathVariable Integer userID, @PathVariable Integer simulation) {
        try {
            Optional<Integer> participant = simulationParticipantsService.getSimulationParticipantsIDIDByUserIDAndSimulationID(userID, simulation);

            if(participant.isPresent()){
                return Response.response(HttpStatus.OK, "Simulation Participants ID retrieved successfully",
                        Map.of("simulationParticipantsID", participant.get()));
            } else {
                return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Simulation Participants ID " + simulation + " not found!");
            }

        } catch(NoSuchElementException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (Exception ex) {
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @GetMapping("/student_game_assessment/{simulationParticipantsID}")
    public ResponseEntity<Object> studentGameAssessment(@PathVariable int simulationParticipantsID) {
        try {
            Optional<SimulationParticipantsEntity> participant = simulationParticipantsService.getParticipantById(simulationParticipantsID);
            return participant.map(value -> Response.response(HttpStatus.OK, "Participant retrieved successfully", value))
                    .orElseGet(() -> NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Participant not found"));
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @PatchMapping("/edit/simulation/{simulationID}")
    public ResponseEntity<Object> updateSimulationParticipant(@RequestBody SimulationParticipantsEntity participant, @PathVariable  int simulationID) {
        try {
            SimulationParticipantsEntity updatedParticipant = simulationParticipantsService.updateParticipant(participant, simulationID);
            return Response.response(HttpStatus.OK, "Participant updated successfully", updatedParticipant);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }


    @PatchMapping("/update_average/user/{userID}/simulation/{simulationID}")
    public ResponseEntity<Object> updateParticipantAttribute(
            @PathVariable int userID,
            @PathVariable int simulationID) {
        try {
            SimulationParticipantsEntity updatedParticipant = simulationParticipantsService.updateParticipantAttribute(userID, simulationID);
            return Response.response(HttpStatus.OK, "Simulation participant updated successfully", updatedParticipant);
        } catch (NoSuchElementException e) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
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

    @GetMapping("/view_simulation_participant_by/simulationParticipant/{simulationParticipantsID}/simulation/{simulationID}")
    public ResponseEntity<Object> getOneBySimulationParticipantsIDAndSimulationID(
            @PathVariable int simulationParticipantsID,
            @PathVariable int simulationID) {
        try {
            Optional<SimulationParticipantsEntity> participant = simulationParticipantsService.getOneBySimulationParticipantsIDAndSimulationID(simulationParticipantsID, simulationID);
            if (participant.isPresent()) {
                return Response.response(HttpStatus.OK, "Simulation Participant retrieved successfully", participant.get());
            } else {
                return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Simulation Participant not found");
            }
        } catch (NoSuchElementException e) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/view_simulation_participant_by/user/{userID}/simulation/{simulationID}")
    public ResponseEntity<Object> getOneByUserIDAndSimulationID(
            @PathVariable Integer userID,
            @PathVariable Integer simulationID) {
        try {
            Optional<SimulationParticipantsEntity> participant = simulationParticipantsService.getOneByUserIDAndSimulationID(userID, simulationID);
            if (participant.isPresent()) {
                return Response.response(HttpStatus.OK, "Simulation Participant retrieved successfully", participant.get());
            } else {
                return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Simulation Participant not found");
            }
        } catch (NoSuchElementException e) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
