package typecasters.tower_of_words.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import typecasters.tower_of_words.Entity.ArchiveAchievementEntity;
import typecasters.tower_of_words.Response.NoDataResponse;
import typecasters.tower_of_words.Response.Response;
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
    public ResponseEntity<Object> insertArchiveAchievement(@RequestBody ArchiveAchievementEntity achievement) {
        try {
            ArchiveAchievementEntity insertedAchievement = archiveAchievementService.insertArchiveAchievement(achievement.getUserID(), achievement);
            return Response.response(HttpStatus.OK, "Achievement inserted successfully", insertedAchievement);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    // Read
    @GetMapping("/view/{userID}")
    public ResponseEntity<Object> viewAllArchiveAchievement(@PathVariable int userID) {
        try {
            List<ArchiveAchievementEntity> achievements = archiveAchievementService.viewAllArchiveAchievement(userID);
            return Response.response(HttpStatus.OK, "Achievements retrieved successfully", achievements);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/view_by_id/{archiveAchievementID}")
    public ResponseEntity<Object> viewArchiveAchievementByID(@PathVariable int archiveAchievementID) {
        try {
            Optional<ArchiveAchievementEntity> achievement = archiveAchievementService.viewArchiveAchievementByID(archiveAchievementID);
            return achievement.map(value -> Response.response(HttpStatus.OK, "Achievement retrieved successfully", value))
                    .orElseGet(() -> NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Achievement not found"));
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    // Update
    @PutMapping("/edit")
    public ResponseEntity<Object> editArchiveAchievement(@RequestBody ArchiveAchievementEntity achievement) {
        try {
            ArchiveAchievementEntity updatedAchievement = archiveAchievementService.editArchiveAchievement(achievement);
            return Response.response(HttpStatus.OK, "Achievement updated successfully", updatedAchievement);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    // Delete
    @PutMapping("/remove/{archiveAchievementID}")
    public ResponseEntity<Object> removeArchiveAchievement(@PathVariable int archiveAchievementID) {
        try {
            archiveAchievementService.removeArchiveAchievement(archiveAchievementID);
            return NoDataResponse.noDataResponse(HttpStatus.OK, "Achievement removed successfully");
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
}
