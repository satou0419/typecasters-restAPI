package typecasters.tower_of_words.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import typecasters.tower_of_words.Entity.StudentWordProgressEntity;
import typecasters.tower_of_words.Response.NoDataResponse;
import typecasters.tower_of_words.Response.Response;
import typecasters.tower_of_words.Service.StudentWordProgressService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student_word_progress")
public class StudentWordProgressController {

    @Autowired
    private StudentWordProgressService studentWordProgressService;

    @PostMapping("/insert")
    public ResponseEntity<Object> addProgress(@RequestBody StudentWordProgressEntity progress) {
        try {
            StudentWordProgressEntity insertedProgress = studentWordProgressService.addProgress(progress);
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

    @PutMapping("/edit")
    public ResponseEntity<Object> updateProgress(@RequestBody StudentWordProgressEntity progress) {
        try {
            StudentWordProgressEntity updatedProgress = studentWordProgressService.setProgress(progress);
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
