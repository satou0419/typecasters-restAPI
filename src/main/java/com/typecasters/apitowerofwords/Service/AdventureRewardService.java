package com.typecasters.apitowerofwords.Service;

import com.typecasters.apitowerofwords.Entity.AdventureEnemyEntity;
import com.typecasters.apitowerofwords.Entity.AdventureRewardEntity;
import com.typecasters.apitowerofwords.Repository.AdventureRewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AdventureRewardService {

    @Autowired
    private AdventureRewardRepository ar_rep;

    //Create
    public AdventureRewardEntity insertReward(AdventureRewardEntity reward){
        return ar_rep.save(reward);
    }

    //Read
    public List<AdventureRewardEntity> getAllReward(){
        return ar_rep.findAll();
    }

    public Optional<AdventureRewardEntity> getRewardByTowerFloorId(int towerFloorId){
        return ar_rep.findByTowerFloorId(towerFloorId);
    }

    //Update


    @SuppressWarnings({"finally", "ReturnInsideFinallyBlock"})
    public AdventureRewardEntity updateReward(AdventureRewardEntity newRewardDetail, int rewardId){
        AdventureRewardEntity reward = new AdventureRewardEntity();

        try{
            reward = ar_rep.findById(rewardId).get();

            reward.setAdvRewardCredit(newRewardDetail.getAdvRewardCredit());
            reward.setRewardItemOne(newRewardDetail.getRewardItemOne());
            reward.setRewardItemTwo(newRewardDetail.getRewardItemTwo());

        }catch(NoSuchElementException ex){
            throw new NoSuchElementException("Reward does not exist");
        }finally{
            return ar_rep.save(reward);
        }
    }

    //Delete
    public String deleteReward(int rewardId){
        String msg = "reward does not exist";

        if(ar_rep.findById(rewardId).isPresent()){
            ar_rep.deleteById(rewardId);
        }

        return msg;
    }

}
