package typecasters.tower_of_words.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import typecasters.tower_of_words.Entity.AchievementEntity;
import typecasters.tower_of_words.Response.NoDataResponse;
import typecasters.tower_of_words.Response.Response;
import typecasters.tower_of_words.Service.AchievementService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/achievement")
public class AchievementController {

    @Autowired
    private AchievementService achievementService;


    @PostMapping("insert_achievement")
    public ResponseEntity<Object> insertAchievement(@RequestBody AchievementEntity achievement){

        try{
            AchievementEntity insertedAchievement = achievementService.insertAchievement(achievement);
            return Response.response(HttpStatus.CREATED, "Achievement added succesfully!", insertedAchievement);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (Exception ex) {
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @GetMapping("/get_all_achievement")
    public ResponseEntity<Object> getAllAchievements() {
        try{
            List<AchievementEntity> achievements = achievementService.getAllAchievements();
            return Response.response(HttpStatus.OK, "Achievements retrieved successfully!", achievements);
        }catch (Exception e){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/get_achievement_by/{achievementID}")
    public ResponseEntity<Object> getAchievementByAchievementID(@PathVariable int achievementID){
        try{
            Optional<AchievementEntity> achievement = achievementService.getOneAchievementByAchievementID(achievementID);
            if(achievement.isPresent()){
                AchievementEntity realAchievement = achievement.get();
                return Response.response(HttpStatus.OK, "Achievement by ID " + achievementID + " retrieved successfully!", realAchievement);
            }else{
                throw new NoSuchElementException("Achievement by ID " + achievementID + " doesn't exist!");
            }
        }catch (NoSuchElementException ex){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }catch (Exception ex){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @PatchMapping("/update_achievement_by/{achievementID}")
    public ResponseEntity<Object> updateAchievement(@PathVariable int achievementID, @RequestBody AchievementEntity newAchievementDetails){
        try{
            AchievementEntity achievement = achievementService.updateAchievement(achievementID, newAchievementDetails);
            return Response.response(HttpStatus.OK, "Achievement updated successfully!", achievement);
        }catch (NoSuchElementException ex){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Achievement is not found!");
        }catch (Exception ex){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @DeleteMapping("/delete_achievement_by/{achievementID}")
    public ResponseEntity<Object> deleteAchievement(@PathVariable int achievementID){
        try{
            achievementService.deleteAchievement(achievementID);
            return NoDataResponse.noDataResponse(HttpStatus.NO_CONTENT, "Achievement ID# " + achievementID + " deleted successfully!");
        }catch (NoSuchElementException ex){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
