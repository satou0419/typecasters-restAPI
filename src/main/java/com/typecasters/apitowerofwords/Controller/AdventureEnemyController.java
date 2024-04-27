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
    private AdventureEnemyService ae_serv;

    @PostMapping("/add_enemy")
    public AdventureEnemyEntity insertEnemy(@RequestBody AdventureEnemyEntity enemy){
        return ae_serv.insertEnemy(enemy);
    }

    //Read All
    @GetMapping("/get_all_enemy")
    public List<AdventureEnemyEntity> getAllEnemy(){
        return ae_serv.getAllEnemy();
    }

    //Read One
    @GetMapping("/get_enemy_by_id")
    public Optional<AdventureEnemyEntity> getEnemyById(@RequestParam int enemy_id){
        return ae_serv.getEnemyById(enemy_id);
    }

    //Read By foreign key
    @GetMapping("/get_enemy_by_tower_id")
    public Optional<List<AdventureEnemyEntity>> getEnemyByTowerId(@RequestParam int tower_id){
        return ae_serv.getAllByTowerId(tower_id);
    }

    //Update
    @PutMapping("/update_enemy")
    public AdventureEnemyEntity updateEnemy(@RequestParam int enemy_id, @RequestBody AdventureEnemyEntity newEnemyDetails){
        return ae_serv.updateEnemy(enemy_id, newEnemyDetails);
    }

    @PutMapping("/delete_enemy")
    public String deleteEnemy(@RequestParam int enemy_id){
        return ae_serv.deleteEnemy(enemy_id);
    }
}
