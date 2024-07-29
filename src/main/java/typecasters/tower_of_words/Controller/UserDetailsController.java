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

    @PatchMapping("/update_user_credit/{ud_id}/{s_credit}")
    public ResponseEntity<Object> updateUserCredit(@PathVariable int ud_id, @PathVariable int s_credit){
        try {
            String result = userDetailsService.updateUserCredit(ud_id, s_credit);
            return Response.response(HttpStatus.OK, "User credit updated successfully", result);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }catch (Exception e){
            return  NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR , e.getMessage());
        }
    }

    @PatchMapping("/increment_word")
    public ResponseEntity<Object> incrementUserWordCount(@RequestParam int ud_id){
        try {
            String result = userDetailsService.incrementUserDetailWords(ud_id);
            return Response.response(HttpStatus.OK, "User word count incremented successfully", result);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }catch (Exception e){
            return  NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR , e.getMessage());
        }
    }

    @PatchMapping("/increment_achievement")
    public ResponseEntity<Object> incrementUserAchievementCount(@RequestParam int ud_id){
        try {
            String result = userDetailsService.incrementUserAchievementCount(ud_id);
            return Response.response(HttpStatus.OK, "User achievement count incremented successfully", result);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }catch (Exception e){
            return  NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR , e.getMessage());
        }
    }

    @PatchMapping("/increment_floor")
    public ResponseEntity<Object> incrementUserFloorCount(@RequestParam int ud_id){
        try{
            String result = userDetailsService.incrementFloorCount(ud_id);
            return Response.response(HttpStatus.OK, "Floor count incremented successfully!", result);
        }catch (IllegalArgumentException e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }catch (Exception e){
            return  NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR , e.getMessage());
        }
    }

    @GetMapping("/get_user_detail")
    public ResponseEntity<Object> getUserDetails(@RequestParam int user_id){
        try {
            UserDetailsEntity userDetails = userDetailsService.getUserDetails(user_id);
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
    public ResponseEntity<Object> getCreditAmountByUserDetailsId(@RequestParam int userDetailId){
        try {
            Optional<Integer> creditAmount = userDetailsService.getCreditAmountByUserDetailId(userDetailId);
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
    public ResponseEntity<Object> updateEquippedCharacter(@RequestParam int userDetailId, @RequestParam String newEquippedCharacter) {
        try{
            String equippedCharacterUpdate = userDetailsService.updateEquippedCharacter(userDetailId, newEquippedCharacter);
            return NoDataResponse.noDataResponse(HttpStatus.OK, equippedCharacterUpdate);
        }catch (RuntimeException e){
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }catch (Exception e){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PatchMapping("/update_badge_display")
    public ResponseEntity<Object> updateBadgeDisplay(@RequestParam int userDetailId, @RequestParam String newBadgeDisplay) {
        try{
            String updatedBadgeDisplay = userDetailsService.updateBadgeDisplay(userDetailId, newBadgeDisplay);
            return NoDataResponse.noDataResponse(HttpStatus.OK, updatedBadgeDisplay);
        }catch (RuntimeException e){
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }catch (Exception e){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
