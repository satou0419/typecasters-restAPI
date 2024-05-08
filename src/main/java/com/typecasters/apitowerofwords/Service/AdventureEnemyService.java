package com.typecasters.apitowerofwords.Service;

import com.typecasters.apitowerofwords.Entity.AdventureEnemyEntity;
import com.typecasters.apitowerofwords.Repository.AdventureEnemyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AdventureEnemyService {

    @Autowired
    private AdventureEnemyRepository ae_rep;

    //Create
    public AdventureEnemyEntity insertEnemy(AdventureEnemyEntity enemy){
        return ae_rep.save(enemy);
    }

    //Read
    public List<AdventureEnemyEntity> getAllEnemy(){
        return ae_rep.findAll();
    }

    public Optional<AdventureEnemyEntity> getEnemyById(int enemy_id){
        return ae_rep.findById(enemy_id);
    }

    //Get All By TowerId

    public Optional<List<AdventureEnemyEntity>> getAllByFloorId(int tower_id){
        return ae_rep.findAllByTowerFloorId(tower_id);
    }

    //Update
    @SuppressWarnings({"finally", "ReturnInsideFinallyBlock"})
    public AdventureEnemyEntity updateEnemy(int enemy_id, AdventureEnemyEntity newEnemyDetail){
        AdventureEnemyEntity enemy = new AdventureEnemyEntity();

        try{
            enemy = ae_rep.findById(enemy_id).get();

            enemy.setImagePath(newEnemyDetail.getImagePath());
            enemy.setTowerFloorId(newEnemyDetail.getTowerFloorId());
            enemy.setWords(newEnemyDetail.getWords());
        }catch (NoSuchElementException ex){
            throw new NoSuchElementException("Enemy does not exist");
        }finally{
            return ae_rep.save(enemy);
        }
    }

    //Delete
    public String deleteEnemy(int enemy_id){
        String msg = "enemy id does not exist";

        if(ae_rep.findById(enemy_id).isPresent()){
            ae_rep.deleteById(enemy_id);
        }

        return msg;

    }


}
