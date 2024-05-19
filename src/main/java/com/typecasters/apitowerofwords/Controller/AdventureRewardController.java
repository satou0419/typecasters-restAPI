package com.typecasters.apitowerofwords.Controller;

import com.typecasters.apitowerofwords.Entity.AdventureEnemyEntity;
import com.typecasters.apitowerofwords.Entity.AdventureRewardEntity;
import com.typecasters.apitowerofwords.Service.AdventureRewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/adventure_reward")
public class AdventureRewardController {

    @Autowired
    private AdventureRewardService ar_serv;

    @PostMapping("/add_reward")
    public AdventureRewardEntity insertReward(@RequestBody AdventureRewardEntity reward){
        return ar_serv.insertReward(reward);
    }

    //Read
    @GetMapping("/get_all_reward")
    public List<AdventureRewardEntity> getAllReward(){
        return ar_serv.getAllReward();
    }

    @GetMapping("/get_reward_by_floor_id")
    public Optional<AdventureRewardEntity> getRewardByTowerFloor(@RequestParam int floor_id){
        return ar_serv.getRewardByTowerFloorId(floor_id);
    }


    //Update
    @PutMapping("/update_reward")
    public AdventureRewardEntity updateReward(@RequestParam int reward_id, @RequestParam AdventureRewardEntity newRewardDetail){
        return ar_serv.updateReward(newRewardDetail, reward_id);
    }

    //Delete
    @DeleteMapping("/delete_reward")
    public String deleteReward(@RequestParam int rewardId){
        return ar_serv.deleteReward(rewardId);
    }
}
