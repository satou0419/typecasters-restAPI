package typecasters.tower_of_words.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import typecasters.tower_of_words.Entity.SimulationAssessmentEntity;
import typecasters.tower_of_words.Response.NoDataResponse;
import typecasters.tower_of_words.Response.Response;
import typecasters.tower_of_words.Service.SimulationAssessmentService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/simulation_assessment")
public class SimulationAssessmentController {

    @Autowired
    SimulationAssessmentService simulationAssessmentService;

    @PostMapping("/insert_new/{simulationID}")
    public ResponseEntity<Object> insertSimulationAssessment(
            @RequestBody SimulationAssessmentEntity simulationAssessment,
            @PathVariable int simulationID) {
        try {
            SimulationAssessmentEntity createdAssessment = simulationAssessmentService.insertSimulationWord(simulationAssessment, simulationID);
            return Response.response(HttpStatus.CREATED, "Simulation Assessment created successfully", createdAssessment);
        } catch (NoSuchElementException e) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get_all_simulation_assessments")
    public ResponseEntity<Object> getAllSimulationAssessments() {
        try {
            List<SimulationAssessmentEntity> assessments = simulationAssessmentService.getAllSimulationAssessment();
            return Response.response(HttpStatus.OK, "Simulation Assessments retrieved successfully", assessments);
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/view_simulation_assessment_by/{simulationAssessmentID}")
    public ResponseEntity<Object> getSimulationAssessmentByID(@PathVariable int simulationAssessmentID) {
        try {
            Optional<SimulationAssessmentEntity> assessment = simulationAssessmentService.getSimulationAssessmentByID(simulationAssessmentID);
            if (assessment.isPresent()) {
                return Response.response(HttpStatus.OK, "Simulation Assessment retrieved successfully", assessment.get());
            } else {
                return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Simulation Assessment not found");
            }
        } catch (NoSuchElementException e) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/view_by_simulation/{simulationID}")
    public ResponseEntity<Object> getSimulationAssessmentBySimulationID(@PathVariable int simulationID) {
        try {
            Optional<SimulationAssessmentEntity> assessment = simulationAssessmentService.getSimulationAssessmentBySimulationID(simulationID);
            if (assessment.isPresent()) {
                return Response.response(HttpStatus.OK, "Simulation Assessment retrieved successfully", assessment.get());
            } else {
                return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Simulation Assessment not found");
            }
        } catch (NoSuchElementException e) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PatchMapping("/update/{simulationID}/{simulationAssessmentID}")
    public ResponseEntity<Object> updateSimulationAssessmentBySimulationID(
            @RequestBody SimulationAssessmentEntity simulationAssessment,
            @PathVariable int simulationID,
            @PathVariable int simulationAssessmentID) {
        try {
            SimulationAssessmentEntity updatedAssessment = simulationAssessmentService.updateSimulationAssessmentBySimulationID(
                    simulationAssessment, simulationID, simulationAssessmentID);
            return Response.response(HttpStatus.OK, "Simulation Assessment updated successfully", updatedAssessment);
        } catch (NoSuchElementException e) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PatchMapping("/update_simulation_assessment_average/simulation/{simulationID}")
    public ResponseEntity<Object> updateSimulationAssessmentAggregates(@PathVariable int simulationID) {
        try {
            SimulationAssessmentEntity updatedAssessment = simulationAssessmentService.updateSimulationAssessment(simulationID);
            return Response.response(HttpStatus.OK, "Simulation Assessment aggregates updated successfully", updatedAssessment);
        } catch (NoSuchElementException e) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/delete_simulation_assessment_by/{simulationAssessmentID}")
    public ResponseEntity<Object> deleteSimulationAssessment(@PathVariable int simulationAssessmentID) {
        try {
            simulationAssessmentService.deleteSimulationAssessment(simulationAssessmentID);
            return NoDataResponse.noDataResponse(HttpStatus.OK, "Simulation Assessment deleted successfully");
        } catch (NoSuchElementException e) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
