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
public class UserDetailscController {

    @Autowired
    UserDetailsService ud_serv;

    @PatchMapping("/update_user_credit/{ud_id}/{s_credit}")
    public ResponseEntity<Object> updateUserCredit(@PathVariable int ud_id, @PathVariable int s_credit){
        try {
            String result = ud_serv.updateUserCredit(ud_id, s_credit);
            return Response.response(HttpStatus.OK, "User credit updated successfully", result);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @PatchMapping("/increment_word")
    public ResponseEntity<Object> incrementUserWordCount(@RequestParam int ud_id){
        try {
            String result = ud_serv.incrementUserDetailWords(ud_id);
            return Response.response(HttpStatus.OK, "User word count incremented successfully", result);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @PatchMapping("/increment_achievement")
    public ResponseEntity<Object> incrementUserAchievementCount(@RequestParam int ud_id){
        try {
            String result = ud_serv.incrementUserAchievementCount(ud_id);
            return Response.response(HttpStatus.OK, "User achievement count incremented successfully", result);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @PutMapping("/increment_floor")
    public ResponseEntity<String> incrementUserFloorCount(@RequestParam int ud_id){
        String result = ud_serv.incrementFloorCount(ud_id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/get_user_detail")
    public ResponseEntity<Object> getUserDetails(@RequestParam int user_id){
        try {
            UserDetailsEntity userDetails = ud_serv.getUserDetails(user_id);
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
            Optional<Integer> creditAmount = ud_serv.getCreditAmountByUserDetailId(userDetailId);
            if (creditAmount.isPresent()) {
                return Response.response(HttpStatus.OK, "Credit amount retrieved successfully", creditAmount.get());
            } else {
                return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Credit amount not found");
            }
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
}
