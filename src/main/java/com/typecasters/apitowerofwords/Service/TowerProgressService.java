package com.typecasters.apitowerofwords.Service;

import com.typecasters.apitowerofwords.Entity.TowerProgressEntity;
import com.typecasters.apitowerofwords.Repository.TowerProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TowerProgressService {

    @Autowired
    TowerProgressRepository tp_repo;

    //Update tower progress
    public String updateTowerProgress(TowerProgressEntity towerProgUpdate, int userProgId){
        String message = "User Progress Updated!";

        try{
            TowerProgressEntity towerProgress = tp_repo.findById(userProgId).get();


            towerProgress.setTowerSecProg(towerProgUpdate.getTowerSecProg());
            towerProgress.setFloorId(towerProgUpdate.getFloorId());

            tp_repo.save(towerProgress);
        }catch(NoSuchElementException e){
            message = "User Progress Id Does not Exist";
        }
        return message;
    }
}
