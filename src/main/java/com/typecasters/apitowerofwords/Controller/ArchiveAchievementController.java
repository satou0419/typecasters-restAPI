package com.typecasters.apitowerofwords.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.typecasters.apitowerofwords.Entity.ArchiveAchievementEntity;
import com.typecasters.apitowerofwords.Service.ArchiveAchievementService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/archive_achievement")
public class ArchiveAchievementController {
    @Autowired
    ArchiveAchievementService archiveAchievementService;

    // Create
    @PostMapping("/insert")
    public ResponseEntity<String> insertArchiveAchievement(@RequestBody ArchiveAchievementEntity achievement) {
        ArchiveAchievementEntity insertedAchievement = archiveAchievementService.insertArchiveAchievement(achievement.getUserID(), achievement);
        return ResponseEntity.ok("Achievement Acquired!");
    }

    // Read
    @GetMapping("/view/{userID}")
    public ResponseEntity<List<ArchiveAchievementEntity>> viewAllArchiveAchievement(@PathVariable int userID) {
        List<ArchiveAchievementEntity> achievements = archiveAchievementService.viewAllArchiveAchievement(userID);
        return new ResponseEntity<>(achievements, HttpStatus.OK);
    }

    @GetMapping("/view_by_id/{archiveAchievementID}")
    public ResponseEntity<ArchiveAchievementEntity> viewArchiveAchievementByID(@PathVariable int archiveAchievementID) {
        Optional<ArchiveAchievementEntity> achievement = archiveAchievementService.viewArchiveAchievementByID(archiveAchievementID);
        return achievement.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update
    @PutMapping("/edit")
    public ResponseEntity<String> editArchiveAchievement(@RequestBody ArchiveAchievementEntity achievement) {
        ArchiveAchievementEntity updatedAchievement = archiveAchievementService.editArchiveAchievement(achievement);
        return ResponseEntity.ok("Achievement Updated!");
    }

    // Delete
    @PutMapping("/remove/{archiveAchievementID}")
    public ResponseEntity<String> removeArchiveAchievement(@PathVariable int archiveAchievementID) {
        archiveAchievementService.removeArchiveAchievement(archiveAchievementID);
        return ResponseEntity.ok("Achievement Removed!");
    }
}
