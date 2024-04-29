package com.typecasters.apitowerofwords.Controller;

import com.typecasters.apitowerofwords.Entity.TowerProgressEntity;
import com.typecasters.apitowerofwords.Service.TowerProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tower_progress")
public class TowerProgressController {

    @Autowired
    TowerProgressService tp_serv;

    @PutMapping("/update_user_progress")
    String updateUserProgress(@RequestBody TowerProgressEntity updatedProgress, @RequestParam int user_prog_id){
        return tp_serv.updateTowerProgress(updatedProgress, user_prog_id);
    }
}
