package typecasters.tower_of_words.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import typecasters.tower_of_words.Entity.SimulationAttemptsEntity;
import typecasters.tower_of_words.Entity.SimulationEntity;
import typecasters.tower_of_words.Entity.SimulationParticipantsEntity;
import typecasters.tower_of_words.Entity.StudentWordProgressEntity;
import typecasters.tower_of_words.Response.NoDataResponse;
import typecasters.tower_of_words.Response.Response;
import typecasters.tower_of_words.Service.SimulationAttemptsService;
import typecasters.tower_of_words.Service.SimulationParticipantsService;
import typecasters.tower_of_words.Service.SimulationService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/simulation_attempts")
public class SimulationAttemptsController {

//    @Autowired
//    private SimulationAttemptsService simulationAttemptsService;
//
//    @Autowired
//    private SimulationService simulationService;
//
//    @Autowired
//    private SimulationParticipantsService simulationParticipantsService;
//
//    @PostMapping("/insert")
//    public ResponseEntity<Object> insertNewAttempt(
//            @RequestBody SimulationAttemptsEntity newAttempt,
//            @RequestParam int simulationID,
//            @RequestParam int simulationParticipantsID) {
//        try {
//            SimulationAttemptsEntity insertedAttempt = simulationAttemptsService.insertNewAttempt(newAttempt, simulationID, simulationParticipantsID);
//            return Response.response(HttpStatus.OK, "Simulation attempt added successfully", insertedAttempt);
//        } catch (Exception e) {
//            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//    }
//
//    @GetMapping("/get_all_attempts")
//    public ResponseEntity<Object> getAllAttempts() {
//        try {
//            List<SimulationAttemptsEntity> attempts = simulationAttemptsService.getAllAttempts();
//            return Response.response(HttpStatus.OK, "All simulation attempts retrieved successfully", attempts);
//        } catch (Exception e) {
//            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//    }
//
//    @GetMapping("/get_attempt_by/{attemptID}")
//    public ResponseEntity<Object> getAttemptByID(@PathVariable int attemptID) {
//        try {
//            Optional<SimulationAttemptsEntity> attempt = simulationAttemptsService.getAttemptByID(attemptID);
//            if (attempt.isPresent()) {
//                return Response.response(HttpStatus.OK, "Simulation attempt retrieved successfully", attempt.get());
//            } else {
//                return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Simulation attempt not found");
//            }
//        } catch (Exception e) {
//            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//    }
//
//    @GetMapping("/get_attempt_by/simulation/{simulationID}/simulationParticipants/{simulationParticipantsID}/current_attempt/{currentAttempts}")
//    public ResponseEntity<Object> getOneBySimulationIDAndSimulationParticipantsID(
//            @PathVariable int simulationID,
//            @PathVariable int simulationParticipantsID,
//            @PathVariable int currentAttempts)
//    {
//        try{
//            Optional<SimulationAttemptsEntity> attemptCheck = simulationAttemptsService
//                    .findOneBySimulationIDAndSimulationParticipantsID(
//                            simulationID,
//                            simulationParticipantsID,
//                            currentAttempts);
//
//            if(attemptCheck.isPresent()){
//                return Response.response(
//                        HttpStatus.OK
//                        , "Simulation Attempt " + attemptCheck.get().getSimulationAttemptsID() + " retrieved successfully!"
//                        , attemptCheck.get());
//            }else{
//                return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Simulation Attempt doesn't exist!");
//            }
//        }catch(NoSuchElementException ex){
//            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
//        }catch(Exception ex){
//            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
//        }
//
//    }
//
//    @GetMapping("/words_progress/{simulationParticipantsID}")
//    public ResponseEntity<Object> wordsProgress(@PathVariable int simulationParticipantsID) {
//        try {
//            List<StudentWordProgressEntity> memberIds = simulationAttemptsService.wordsProgress(simulationParticipantsID);
//            if (memberIds.isEmpty()) {
//                return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "No word progress found for the given participant ID");
//            }
//            return Response.response(HttpStatus.OK, "Word progress retrieved successfully", memberIds);
//        } catch (IllegalArgumentException ex) {
//            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
//        }
//    }
//
//    @PutMapping("/update_attempt_by/{attemptID}")
//    public ResponseEntity<Object> updateAttemptByID(@PathVariable int attemptID, @RequestBody SimulationAttemptsEntity attempts) {
//        try {
//            SimulationAttemptsEntity updatedAttempt = simulationAttemptsService.updateAttemptByID(attemptID, attempts);
//            return Response.response(HttpStatus.OK, "Simulation attempt updated successfully", updatedAttempt);
//        } catch (NoSuchElementException e) {
//            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
//        } catch (Exception e) {
//            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//    }
//
//    @PutMapping("/update_attempt_by/simulation/{simulationID}/participant/{participantID}/current_attempt/{currentAttempts}")
//    public ResponseEntity<Object> updateAttemptBySimulationIDAndSimulationParticipantsID(
//            @PathVariable int simulationID,
//            @PathVariable int participantID,
//            @PathVariable int currentAttempts,
//            @RequestBody SimulationAttemptsEntity simulationAttemptsEdit) {
//        try {
//            SimulationAttemptsEntity updatedAttempt = simulationAttemptsService.updateAttemptBySimulationIDAndSimulationParticipantsID(
//                    simulationID,
//                    participantID,
//                    currentAttempts,
//                    simulationAttemptsEdit);
//
//            return Response.response(HttpStatus.OK, "Simulation attempt updated successfully", updatedAttempt);
//        } catch (NoSuchElementException e) {
//            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
//        } catch (Exception e) {
//            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//    }
//
//    @GetMapping("/get_all_by/simulation/{simulationID}/participant/{participantID}")
//    public ResponseEntity<Object> getAttemptsBySimulationIDAndParticipantID(
//            @PathVariable int simulationID,
//            @PathVariable int participantID) {
//        try {
//            List<SimulationAttemptsEntity> attempts = simulationAttemptsService.getBySimulationIDAndSimulationParticipantsID(simulationID, participantID);
//            if (attempts.isEmpty()) {
//                return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "No simulation attempts found for the given simulation ID and participant ID");
//            }
//            return Response.response(HttpStatus.OK, "Simulation attempts retrieved successfully!", attempts);
//        } catch (Exception e) {
//            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//    }
//
//
//    @DeleteMapping("/remove/{attemptID}")
//    public ResponseEntity<Object> deleteAttempt(@PathVariable int attemptID) {
//        try {
//            String message = simulationAttemptsService.deleteAttempt(attemptID);
//            return NoDataResponse.noDataResponse(HttpStatus.OK, message);
//        } catch (NoSuchElementException e) {
//            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
//        } catch (Exception e) {
//            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//    }
}
