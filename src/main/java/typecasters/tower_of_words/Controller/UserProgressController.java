package typecasters.tower_of_words.Controller;

import typecasters.tower_of_words.Entity.UserProgressEntity;
import typecasters.tower_of_words.Service.UserProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import typecasters.tower_of_words.Response.NoDataResponse;
import typecasters.tower_of_words.Response.Response;

@RestController
@RequestMapping("tower_progress")
public class UserProgressController {

    @Autowired
    UserProgressService userProgressService;

    @PutMapping("/update_user_progress")
    public ResponseEntity<Object> updateUserProgress(@RequestBody UserProgressEntity updatedProgress, @RequestParam int userProgressID) {
        try {
            String result = userProgressService.updateTowerProgress(updatedProgress, userProgressID);
            return Response.response(HttpStatus.OK, "User progress updated successfully", result);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

}
