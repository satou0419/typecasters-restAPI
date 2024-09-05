package typecasters.tower_of_words.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import typecasters.tower_of_words.Entity.ArchiveAchievementEntity;
import typecasters.tower_of_words.Entity.AchievementEntity;
import typecasters.tower_of_words.Response.NoDataResponse;
import typecasters.tower_of_words.Response.Response;
import typecasters.tower_of_words.Service.ArchiveAchievementService;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/archive_achievement")
public class ArchiveAchievementController {

    @Autowired
    private ArchiveAchievementService archiveAchievementService;

    @PostMapping("/add_archive_achievement")
    public ResponseEntity<Object> insertArchiveAchievement(@RequestBody ArchiveAchievementEntity archiveAchievement) {
        try {
            ArchiveAchievementEntity createdAchievement = archiveAchievementService.insertArchiveAchievement(archiveAchievement);
            return Response.response(HttpStatus.OK, "Archive achievement added successfully", createdAchievement);
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get_all_archive_achievements")
    public ResponseEntity<Object> getAllArchiveAchievements() {
        try {
            List<ArchiveAchievementEntity> achievements = archiveAchievementService.getAllArchiveAchievement();
            return Response.response(HttpStatus.OK, "Archive achievements retrieved successfully", achievements);
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get_archive_achievement_by_id")
    public ResponseEntity<Object> getArchiveAchievementById(@RequestParam int archiveAchievementID) {
        try {
            Optional<ArchiveAchievementEntity> achievement = archiveAchievementService.getArchiveAchievementByID(archiveAchievementID);
            return achievement.map(value -> Response.response(HttpStatus.OK, "Archive achievement retrieved successfully", value))
                    .orElseGet(() -> NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Archive achievement ID# " + archiveAchievementID + " not found."));
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get_archive_achievements_by_user_id")
    public ResponseEntity<Object> getArchiveAchievementsByUserId(@RequestParam int userID) {
        try {
            List<ArchiveAchievementEntity> achievements = archiveAchievementService.getArchiveAchievementsByUserID(userID);
            return Response.response(HttpStatus.OK, "Archive achievements retrieved for user ID " + userID, achievements);
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get_archive_achievement_by_user_id_and_achievement_id")
    public ResponseEntity<Object> getArchiveAchievementByUserIdAndAchievementId(
            @RequestParam int userID, @RequestParam int achievementID) {
        try {
            Optional<ArchiveAchievementEntity> achievement =
                    archiveAchievementService.getArchiveAchievementByUserIDAndAchievementID(userID, achievementID);
            return achievement.map(value -> Response.response(HttpStatus.OK, "Archive achievement retrieved successfully", value))
                    .orElseGet(() -> NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Archive achievement for user ID# " + userID + " and achievement ID# " + achievementID + " not found."));
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/update_archive_achievement")
    public ResponseEntity<Object> updateArchiveAchievement(@RequestBody ArchiveAchievementEntity newArchiveAchievementDetails) {
        try {
            ArchiveAchievementEntity updatedAchievement = archiveAchievementService.updateArchiveAchievement(newArchiveAchievementDetails);
            return Response.response(HttpStatus.OK, "Archive achievement updated successfully!", updatedAchievement);
        } catch (NoSuchElementException e) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/prepopulate_new_achievement")
    public ResponseEntity<Object> prepopulateNewAchievementForExistingUsers(@RequestBody AchievementEntity newAchievement) {
        try {
            archiveAchievementService.prepopulateNewAchievementForExistingUsers(newAchievement);
            return NoDataResponse.noDataResponse(HttpStatus.OK, "New achievement prepopulated for existing users.");
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/check_user_eligibility")
    public ResponseEntity<Object> checkUserEligibilityForAchievements(
            @RequestParam int userID, @RequestParam String achievementType) {
        try {
            List<AchievementEntity> unlockedAchievements = archiveAchievementService.checkUserEligibilityForAchievements(userID, achievementType);
            return Response.response(HttpStatus.OK, "Checked user eligibility for achievements", unlockedAchievements);
        } catch (IllegalArgumentException e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PatchMapping("/for_notifications")
    public ResponseEntity<Object> forNotificationsArchiveAchievement(
            @RequestParam int userID, @RequestParam int achievementID) {
        try {
            ArchiveAchievementEntity updatedAchievement = archiveAchievementService.forNotificationsArchiveAchievement(userID, achievementID);
            return Response.response(HttpStatus.OK, "Archive achievement updated for notifications", updatedAchievement);
        } catch (NoSuchElementException e) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}