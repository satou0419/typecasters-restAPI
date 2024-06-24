package typecasters.tower_of_words.Controller;

import typecasters.tower_of_words.Entity.UserProgressEntity;
import typecasters.tower_of_words.Service.UserProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tower_progress")
public class UserProgressController {

    @Autowired
    UserProgressService tp_serv;

    @PutMapping("/update_user_progress")
    String updateUserProgress(@RequestBody UserProgressEntity updatedProgress, @RequestParam int user_prog_id){
        return tp_serv.updateTowerProgress(updatedProgress, user_prog_id);
    }
}
