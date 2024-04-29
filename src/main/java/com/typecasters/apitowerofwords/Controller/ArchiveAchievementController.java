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

    @GetMapping("/view_by_id/{AAID}")
    public Optional<ArchiveAchievementEntity> viewArchiveAchievementByID(@PathVariable int AAID) {
        return AAS.viewArchiveAchievementByID(AAID);
    }

    //Update
    @PutMapping("/edit")
    public ArchiveAchievementEntity editArchiveAchievement(@RequestBody ArchiveAchievementEntity achievement) {
        return AAS.editArchiveAchievement(achievement);
    }

    //Delete
    @PutMapping("/remove/{AAID}")
    public ArchiveAchievementEntity removeArchiveAchievement(@PathVariable int AAID) {
        return AAS.removeArchiveAchievement(AAID);
    }
}
