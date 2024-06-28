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
    UserProgressService tp_serv;

    @PutMapping("/update_user_progress")
    public ResponseEntity<Object> updateUserProgress(@RequestBody UserProgressEntity updatedProgress, @RequestParam int user_prog_id) {
        try {
            String result = tp_serv.updateTowerProgress(updatedProgress, user_prog_id);
            return Response.response(HttpStatus.OK, "User progress updated successfully", result);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

}
