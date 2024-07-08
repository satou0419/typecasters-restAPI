package typecasters.tower_of_words.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import typecasters.tower_of_words.Entity.UserArchiveAchievementEntity;
import typecasters.tower_of_words.Response.NoDataResponse;
import typecasters.tower_of_words.Response.Response;
import typecasters.tower_of_words.Service.UserArchiveAchievementService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user_archive_achievement")
public class UserArchiveAchievementController {
    @Autowired
    private UserArchiveAchievementService userArchiveAchievementService;

    @PostMapping("/insert")
    public ResponseEntity<Object> insertUserArchiveAchievement(@RequestBody UserArchiveAchievementEntity user) {
        try {
            UserArchiveAchievementEntity insertedUser = userArchiveAchievementService.insertUserArchiveAchievement(user);
            return Response.response(HttpStatus.OK, "User archive achievement inserted successfully", insertedUser);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/view")
    public ResponseEntity<Object> viewAllUserArchiveAchievement() {
        try {
            List<UserArchiveAchievementEntity> userAchievements = userArchiveAchievementService.viewAllUserArchiveAchievement();
            return Response.response(HttpStatus.OK, "All user archive achievements retrieved successfully", userAchievements);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<Object> editUserArchiveAchievement(@RequestBody UserArchiveAchievementEntity user) {
        try {
            UserArchiveAchievementEntity updatedUser = userArchiveAchievementService.editUserArchiveAchievement(user);
            return Response.response(HttpStatus.OK, "User archive achievement updated successfully", updatedUser);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Object> deleteUserArchiveAchievement(@PathVariable int id) {
        try {
            userArchiveAchievementService.deleteUserArchiveAchievement(id);
            return NoDataResponse.noDataResponse(HttpStatus.OK, "User archive achievement deleted successfully");
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
}
