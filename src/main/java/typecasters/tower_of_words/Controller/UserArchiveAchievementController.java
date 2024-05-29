package typecasters.tower_of_words.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import typecasters.tower_of_words.Entity.UserArchiveAchievementEntity;
import typecasters.tower_of_words.Service.UserArchiveAchievementService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user_archive_achievement")
public class UserArchiveAchievementController {
    @Autowired
    private UserArchiveAchievementService userArchiveAchievementService;

    @PostMapping("/insert")
    public ResponseEntity<UserArchiveAchievementEntity> insertSimulationWord(@RequestBody UserArchiveAchievementEntity user) {
        UserArchiveAchievementEntity insertedWord = userArchiveAchievementService.insertUserArchiveAchievement( user);
        return new ResponseEntity<>(insertedWord, HttpStatus.OK);
    }

    @GetMapping("/view")
    public ResponseEntity<List<UserArchiveAchievementEntity>> getAllSimulationWords() {
        List<UserArchiveAchievementEntity> words = userArchiveAchievementService.viewAllUserArchiveAchievement();
        return new ResponseEntity<>(words, HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<UserArchiveAchievementEntity> updateSimulationWord(@RequestBody UserArchiveAchievementEntity word) {
        UserArchiveAchievementEntity updatedWord = userArchiveAchievementService.editUserArchiveAchievement(word);
        return new ResponseEntity<>(updatedWord, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Void> deleteSimulationWord(@PathVariable int id) {
        userArchiveAchievementService.deleteUserArchiveAchievement( id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
