package typecasters.tower_of_words.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import typecasters.tower_of_words.Entity.StudentWordProgressEntity;
import typecasters.tower_of_words.Service.StudentWordProgressService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student_word_progress")
public class StudentWordProgressController {
    @Autowired
    private StudentWordProgressService studentWordProgressService;

    @PostMapping("/insert")
    public ResponseEntity<StudentWordProgressEntity> addProgress(@RequestBody StudentWordProgressEntity progress) {
        StudentWordProgressEntity insertedProgress = studentWordProgressService.addProgress(progress);
        return new ResponseEntity<>(insertedProgress, HttpStatus.OK);
    }

    @GetMapping("/view")
    public ResponseEntity<List<StudentWordProgressEntity>> getAllProgress() {
        List<StudentWordProgressEntity> progressList = studentWordProgressService.getAllProgress();
        return new ResponseEntity<>(progressList, HttpStatus.OK);
    }

    @GetMapping("/view/{progressID}")
    public ResponseEntity<StudentWordProgressEntity> getProgressById(@PathVariable int progressID) {
        Optional<StudentWordProgressEntity> progress = studentWordProgressService.getProgressById(progressID);
        return progress.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/edit")
    public ResponseEntity<StudentWordProgressEntity> updateProgress(@RequestBody StudentWordProgressEntity progress) {
        StudentWordProgressEntity updatedProgress = studentWordProgressService.setProgress(progress);
        return new ResponseEntity<>(updatedProgress, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{progressID}")
    public ResponseEntity<Void> deleteProgress(@PathVariable int progressID) {
        studentWordProgressService.deleteProgress(progressID);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
