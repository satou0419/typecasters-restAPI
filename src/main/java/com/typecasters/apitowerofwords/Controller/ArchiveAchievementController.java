package com.typecasters.apitowerofwords.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    //Create
    @PostMapping("/insert")
    public ArchiveAchievementEntity insertArchiveAchievement(@RequestBody ArchiveAchievementEntity achievement) {
        return archiveAchievementService.insertArchiveAchievement(achievement.getUserID(), achievement);
    }

    //Read
    @GetMapping("/view/{userID}")
    public List<ArchiveAchievementEntity> viewAllArchiveAchievement(@PathVariable int userID) {
        return archiveAchievementService.viewAllArchiveAchievement(userID);
    }

    @GetMapping("/view_by_id/{archiveAchievementID}")
    public Optional<ArchiveAchievementEntity> viewArchiveAchievementByID(@PathVariable int archiveAchievementID) {
        return archiveAchievementService.viewArchiveAchievementByID(archiveAchievementID);
    }

    //Update
    @PutMapping("/edit")
    public ArchiveAchievementEntity editArchiveAchievement(@RequestBody ArchiveAchievementEntity achievement) {
        return archiveAchievementService.editArchiveAchievement(achievement);
    }

    //Delete
    @PutMapping("/remove/{archiveAchievementID}")
    public ArchiveAchievementEntity removeArchiveAchievement(@PathVariable int archiveAchievementID) {
        return archiveAchievementService.removeArchiveAchievement(archiveAchievementID);
    }
}
