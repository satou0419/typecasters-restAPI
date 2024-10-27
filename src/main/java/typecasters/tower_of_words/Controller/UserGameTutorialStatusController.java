package typecasters.tower_of_words.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import typecasters.tower_of_words.Entity.UserGameTutorialStatusEntity;
import typecasters.tower_of_words.Response.NoDataResponse;
import typecasters.tower_of_words.Response.Response;
import typecasters.tower_of_words.Service.UserGameTutorialStatusService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/user_game_tutorial_status")
public class UserGameTutorialStatusController {

    @Autowired
    private UserGameTutorialStatusService userGameTutorialStatusService;

    @PostMapping("/add_user_game_tutorial_status")
    public ResponseEntity<Object> insertUserGameTutorialStatus(@RequestBody UserGameTutorialStatusEntity userGameTutorialStatus){
        try{
            UserGameTutorialStatusEntity createdUserGameTutorialStatus = userGameTutorialStatusService.insertUserGameTutorialStatus(userGameTutorialStatus);
            return Response.response(HttpStatus.OK, "User Game Tutorial Status created successfully!", createdUserGameTutorialStatus);
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get_all_user_game_tutorial_status")
    public ResponseEntity<Object> getAllUserGameTutorialStatus(){
        try{
            List<UserGameTutorialStatusEntity> userGameTutorialStatus = userGameTutorialStatusService.getAllUserGameTutorialStatus();
            return Response.response(HttpStatus.OK, "All User Game Tutorial Status retrieved successfully!", userGameTutorialStatus);
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get_user_game_tutorial_status_by_user_details_id")
    public ResponseEntity<Object> getUserGameTutorialStatusByUserID(@RequestParam int userDetailsID){
        try{
            Optional<UserGameTutorialStatusEntity> userGameTutorialStatus = userGameTutorialStatusService.getUserGameTutorialStatusByUserDetailsID(userDetailsID);
            return userGameTutorialStatus.map(value -> Response.response(HttpStatus.OK, "User Game Tutorial Status retrieved successfully!", value))
                    .orElseGet(() -> NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "User Game Tutorial Status is not found using this User Details ID => " + userDetailsID));
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PatchMapping("/update_user_game_tutorial_status_body")
    public ResponseEntity<Object> updateUserGameTutorialStatusBody(@RequestBody UserGameTutorialStatusEntity userGameTutorialStatusEntity){
        try{
            UserGameTutorialStatusEntity updatedUserGameTutorialStatus = userGameTutorialStatusService.updateUserGameTutorialStatusByUserDetailsID(userGameTutorialStatusEntity);
            return Response.response(HttpStatus.OK, "User Game Tutorial Status updated successfully!", updatedUserGameTutorialStatus);
        } catch (NoSuchElementException e) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PatchMapping("/update_user_game_tutorial_status_by/{userDetailsID}/by_tutorial_type/{gameType}")
    public ResponseEntity<Object> updateUserGameTutorialStatus(@PathVariable int userDetailsID, @PathVariable String gameType){
        try{
            String userGameTutorialStatusString = userGameTutorialStatusService.updateTutorialGameplayStatusByUserDetailsID(userDetailsID, gameType);
            return NoDataResponse.noDataResponse(HttpStatus.OK, userGameTutorialStatusString);
        } catch (NoSuchElementException ex){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
