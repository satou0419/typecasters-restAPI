package typecasters.tower_of_words.Controller;

import typecasters.tower_of_words.Entity.UserDetailsEntity;
import typecasters.tower_of_words.Response.NoDataResponse;
import typecasters.tower_of_words.Response.Response;
import typecasters.tower_of_words.Service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("user_details")
public class UserDetailsController {

    @Autowired
    UserDetailsService userDetailsService;

    @PatchMapping("/update_user_credit/{userDetailsID}/{creditAmount}")
    public ResponseEntity<Object> updateUserCredit(@PathVariable int userDetailsID, @PathVariable int creditAmount){
        try {
            String result = userDetailsService.updateUserCredit(userDetailsID, creditAmount);
            return Response.response(HttpStatus.OK, "User credit updated successfully", result);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }catch (Exception e){
            return  NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR , e.getMessage());
        }
    }

    @PatchMapping("/increment_word")
    public ResponseEntity<Object> incrementUserWordCount(@RequestParam int userDetailsID){
        try {
            String result = userDetailsService.incrementUserDetailWords(userDetailsID);
            return Response.response(HttpStatus.OK, "User word count incremented successfully", result);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }catch (Exception e){
            return  NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR , e.getMessage());
        }
    }

    @PatchMapping("/increment_achievement")
    public ResponseEntity<Object> incrementUserAchievementCount(@RequestParam int userDetailsID){
        try {
            String result = userDetailsService.incrementUserAchievementCount(userDetailsID);
            return Response.response(HttpStatus.OK, "User achievement count incremented successfully", result);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }catch (Exception e){
            return  NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR , e.getMessage());
        }
    }

    @PatchMapping("/increment_floor")
    public ResponseEntity<Object> incrementUserFloorCount(@RequestParam int userDetailsID){
        try{
            String result = userDetailsService.incrementFloorCount(userDetailsID);
            return Response.response(HttpStatus.OK, "Floor count incremented successfully!", result);
        }catch (IllegalArgumentException e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }catch (Exception e){
            return  NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR , e.getMessage());
        }
    }

    @GetMapping("/get_user_detail")
    public ResponseEntity<Object> getUserDetails(@RequestParam int userID){
        try {
            UserDetailsEntity userDetails = userDetailsService.getUserDetails(userID);
            if (userDetails != null) {
                return Response.response(HttpStatus.OK, "User details retrieved successfully", userDetails);
            } else {
                return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "User details not found");
            }
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/get_credit_amount")
    public ResponseEntity<Object> getCreditAmountByUserDetailsId(@RequestParam int userDetailsID){
        try {
            Optional<Integer> creditAmount = userDetailsService.getCreditAmountByUserDetailId(userDetailsID);
            if (creditAmount.isPresent()) {
                return Response.response(HttpStatus.OK, "Credit amount retrieved successfully", creditAmount.get());
            } else {
                return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Credit amount not found");
            }
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @PatchMapping("/update_equipped_character")
    public ResponseEntity<Object> updateEquippedCharacter(@RequestParam int userDetailsID, @RequestParam String newEquippedCharacter) {
        try{
            String equippedCharacterUpdate = userDetailsService.updateEquippedCharacter(userDetailsID, newEquippedCharacter);
            return NoDataResponse.noDataResponse(HttpStatus.OK, equippedCharacterUpdate);
        }catch (RuntimeException e){
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }catch (Exception e){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PatchMapping("/update_badge_display")
    public ResponseEntity<Object> updateBadgeDisplay(@RequestParam int userDetailsID, @RequestParam String newBadgeDisplay) {
        try{
            String updatedBadgeDisplay = userDetailsService.updateBadgeDisplay(userDetailsID, newBadgeDisplay);
            return NoDataResponse.noDataResponse(HttpStatus.OK, updatedBadgeDisplay);
        }catch (RuntimeException e){
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }catch (Exception e){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
