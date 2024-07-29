package typecasters.tower_of_words.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import typecasters.tower_of_words.Entity.SimulationAttemptsEntity;
import typecasters.tower_of_words.Entity.StudentWordProgressEntity;
import typecasters.tower_of_words.Entity.UserEntity;
import typecasters.tower_of_words.Response.NoDataResponse;
import typecasters.tower_of_words.Response.Response;
import typecasters.tower_of_words.Service.StudentWordProgressService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/student_word_progress")
public class StudentWordProgressController {

    @Autowired
    private StudentWordProgressService studentWordProgressService;

    @PostMapping("/insert/{simulationAttemptID}")
    public ResponseEntity<Object> addProgress(
            @RequestBody StudentWordProgressEntity progress,
            @PathVariable int simulationAttemptID) {
        try {
            StudentWordProgressEntity insertedProgress = studentWordProgressService.addProgress(progress, simulationAttemptID);
            return Response.response(HttpStatus.OK, "Progress added successfully", insertedProgress);
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/view")
    public ResponseEntity<Object> getAllProgress() {
        try {
            List<StudentWordProgressEntity> progressList = studentWordProgressService.getAllProgress();
            return Response.response(HttpStatus.OK, "Progress list retrieved successfully", progressList);
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/view_all_by/{studentID}")
    public ResponseEntity<Object> getAllByStudentID(@PathVariable int studentID){
        try{
            List<StudentWordProgressEntity> studentWordProgressList = studentWordProgressService.getAllByStudentID(studentID);
            return Response.response(HttpStatus.OK, "Student " + studentID + "'s progress list has been retrieved successfully!", studentWordProgressList);
        }catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/view/{progressID}")
    public ResponseEntity<Object> getProgressById(@PathVariable int progressID) {
        try {
            Optional<StudentWordProgressEntity> progress = studentWordProgressService.getProgressById(progressID);
            if (progress.isPresent()) {
                return Response.response(HttpStatus.OK, "Progress retrieved successfully", progress.get());
            } else {
                return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Progress not found");
            }
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/view_all_by/student/{studentID}/simulationAttempt/{simulationAttemptsID}")
    public ResponseEntity<Object> getAllByStudentIDAndSimulationAttemptsID(
            @PathVariable int studentID,
            @PathVariable int simulationAttemptsID)
    {
        try{
            List<StudentWordProgressEntity> wordProgress = studentWordProgressService.getAllByStudentIDAndSimulationAttemptsID(studentID, simulationAttemptsID);
            return Response.response(HttpStatus.OK, "Progress retrieved successfully!", wordProgress);
        }catch (NoSuchElementException ex){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (Exception ex){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @GetMapping("/view_all_by/simulationAttempt/{simulationAttemptsID}")
    public ResponseEntity<Object> getAllBySimulationAttemptsID(
            @PathVariable int simulationAttemptsID)
    {
        try{
            List<StudentWordProgressEntity> wordProgress = studentWordProgressService.getAllBySimulationAttemptsID(simulationAttemptsID);
            return Response.response(HttpStatus.OK,"Progress retrieved successfully!", wordProgress);
        }catch (NoSuchElementException ex){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (Exception ex){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @PutMapping("/edit/{simulationAttemptID}")
    public ResponseEntity<Object> updateProgress(
            @RequestBody StudentWordProgressEntity progress,
            @PathVariable int simulationAttemptID) {
        try {
            StudentWordProgressEntity updatedProgress = studentWordProgressService.updateProgress(progress, simulationAttemptID);
            return Response.response(HttpStatus.OK, "Progress updated successfully", updatedProgress);
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/remove/{progressID}")
    public ResponseEntity<Object> deleteProgress(@PathVariable int progressID) {
        try {
            studentWordProgressService.deleteProgress(progressID);
            return NoDataResponse.noDataResponse(HttpStatus.OK, "Progress deleted successfully");
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}

