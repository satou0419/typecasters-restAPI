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
    ArchiveAchievementService AAS;

    //Create
    @PostMapping("/insert")
    public ArchiveAchievementEntity insertArchiveAchievement(@RequestBody ArchiveAchievementEntity achievement) {
        return AAS.insertArchiveAchievement(achievement);
    }

    //Read
    @GetMapping("/view")
    public List<ArchiveAchievementEntity> viewAllArchiveAchievement() {
        return AAS.viewAllArchiveAchievement();
    }

    @GetMapping("/view_by_id/{archive_achievement_id}")
    public Optional<ArchiveAchievementEntity> viewArchiveAchievementByID(@PathVariable int archive_achievement_id) {
        return AAS.viewArchiveAchievementByID(archive_achievement_id);
    }

    //Update
    @PutMapping("/edit/{archive_achievement_id}")
    public ArchiveAchievementEntity editArchiveAchievement(@PathVariable int archive_achievement_id, @RequestBody ArchiveAchievementEntity new_archive_achievement) {
        return AAS.editArchiveAchievement(archive_achievement_id, new_archive_achievement);
    }

    //Delete
    @PutMapping("/remove/{archive_achievement_id}")
    public ArchiveAchievementEntity removeArchiveAchievement(@PathVariable int archive_achievement_id) {
        return AAS.removeArchiveAchievement(archive_achievement_id);
    }
}
