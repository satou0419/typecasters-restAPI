package typecasters.tower_of_words.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import typecasters.tower_of_words.Entity.ArchiveAchievementEntity;
import typecasters.tower_of_words.Service.ArchiveAchievementService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/archive_achievement")
public class ArchiveAchievementController {
    @Autowired
    ArchiveAchievementService archiveAchievementService;

    // Create
    @PostMapping("/insert")
    public ResponseEntity<ArchiveAchievementEntity> insertArchiveAchievement(@RequestBody ArchiveAchievementEntity achievement) {
        try{
            ArchiveAchievementEntity insertedAchievement = archiveAchievementService.insertArchiveAchievement(achievement.getUserID(), achievement);
            return new ResponseEntity<>(insertedAchievement, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Read
    @GetMapping("/view/{userID}")
    public ResponseEntity<List<ArchiveAchievementEntity>> viewAllArchiveAchievement(@PathVariable int userID) {
        try{
            List<ArchiveAchievementEntity> achievements = archiveAchievementService.viewAllArchiveAchievement(userID);
            return new ResponseEntity<>(achievements, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/view_by_id/{archiveAchievementID}")
    public ResponseEntity<ArchiveAchievementEntity> viewArchiveAchievementByID(@PathVariable int archiveAchievementID) {
        try{
            Optional<ArchiveAchievementEntity> achievement = archiveAchievementService.viewArchiveAchievementByID(archiveAchievementID);
            return achievement.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update
    @PutMapping("/edit")
    public ResponseEntity<ArchiveAchievementEntity> editArchiveAchievement(@RequestBody ArchiveAchievementEntity achievement) {
        try{
            ArchiveAchievementEntity updatedAchievement = archiveAchievementService.editArchiveAchievement(achievement);
            return new ResponseEntity<>(updatedAchievement, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete
    @PutMapping("/remove/{archiveAchievementID}")
    public ResponseEntity<String> removeArchiveAchievement(@PathVariable int archiveAchievementID) {
        try{
            ArchiveAchievementEntity removedAchievement = archiveAchievementService.removeArchiveAchievement(archiveAchievementID);
            return new ResponseEntity<>("Achievement Removed!", HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
