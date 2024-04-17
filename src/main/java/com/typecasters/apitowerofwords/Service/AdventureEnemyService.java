package com.typecasters.apitowerofwords.Service;

import com.typecasters.apitowerofwords.Entity.AdventureEnemyEntity;
import com.typecasters.apitowerofwords.Repository.AdventureEnemyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AdventureEnemyService {
    @Autowired
    AdventureEnemyRepository aerepo;


    //C - read all records in tbladventure_ennemy
    public AdventureEnemyEntity insertAdventureEnemy(AdventureEnemyEntity enemy) {

        return aerepo.save(enemy);
    }

    //R - read all records in tbladventure_ennemy
    public List<AdventureEnemyEntity> getAllAdventureEnemy(){
        return aerepo.findAll();
    }

    //U - update enemy
    @SuppressWarnings("finally")
    public AdventureEnemyEntity updateAdventureEnemy(int enemyId, AdventureEnemyEntity newEnemyDetails) {
        AdventureEnemyEntity enemy = new AdventureEnemyEntity();


        try {
            //search
            enemy = aerepo.findById(enemyId).get();


            //Update
            enemy.setEnemyName(newEnemyDetails.getEnemyName());
            enemy.setWord(newEnemyDetails.getWord());
            enemy.setImagePath(newEnemyDetails.getImagePath());

        }catch(NoSuchElementException ex) {
            throw new NoSuchElementException ("enemy " + enemyId + " does not exist");
        }finally {
            return aerepo.save(enemy);
        }
    }

    //D - delete
    public String deleteEnemy(int enemyId) {
        String msg = "";

        if(aerepo.findById(enemyId) != null) {
            aerepo.deleteById(enemyId);
            msg = "Enemy " + enemyId + " is successfully deleted!";
        }else {
            msg = "Enemy " + enemyId +" does not exist";
        }


        return msg;
    }
}
