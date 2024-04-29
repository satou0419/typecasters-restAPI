package com.typecasters.apitowerofwords.Controller;

import com.typecasters.apitowerofwords.Entity.AdventureEnemyEntity;
import com.typecasters.apitowerofwords.Service.AdventureEnemyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/adventure_enemy")
public class AdventureEnemyController {
    @Autowired
    AdventureEnemyService AES;

    //Create
    @PostMapping("/insert")
    public AdventureEnemyEntity insertAdventureEnemy(@RequestBody AdventureEnemyEntity enemy) {
        return AES.insertAdventureEnemy(enemy);
    }

    //Read
    @GetMapping("/view")
    public List<AdventureEnemyEntity> viewAllAdventureEnemy() {
        return AES.viewAllAdventureEnemy();
    }

    @GetMapping("/view_by_id/{AEID}")
    public Optional<AdventureEnemyEntity> viewAdventureEnemyByID(@PathVariable int AEID) {
        return AES.viewAdventureEnemyByID(AEID);
    }

    //Update
    @PutMapping("/edit")
    public AdventureEnemyEntity editAdventureEnemy(@RequestBody AdventureEnemyEntity enemy) {
        return AES.editAdventureEnemy(enemy);
    }

    //Delete
    @PutMapping("/remove/{AEID}")
    public AdventureEnemyEntity removeAdventureEnemy(@PathVariable int AEID) {
        return AES.removeAdventureEnemy(AEID);
    }
}
