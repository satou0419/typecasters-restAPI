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
    AdventureEnemyRepository AER;

    public AdventureEnemyEntity insertAdventureEnemy(AdventureEnemyEntity enemy) {
        return AER.save(enemy);
    }

    public List<AdventureEnemyEntity> viewAllAdventureEnemy() {
        return AER.findAll();
    }

    public Optional<AdventureEnemyEntity> viewAdventureEnemyByID(int enemyID) {
        return AER.findById(enemyID);
    }

    public AdventureEnemyEntity editAdventureEnemy(AdventureEnemyEntity enemy) {
        AdventureEnemyEntity edit = new AdventureEnemyEntity();

        try {
            edit = AER.findById(enemy.getAEID()).get();

            edit.setImagePath(enemy.getImagePath());
            edit.setTFID(enemy.getTFID());

        }catch(NoSuchElementException ex) {
            throw new NoSuchElementException ("Achievement " + enemy.getAEID() + " does not exist");
        }finally {
            return AER.save(edit);
        }
    }

    public AdventureEnemyEntity removeAdventureEnemy(int enemyID) {
        AdventureEnemyEntity delete = new AdventureEnemyEntity();

        try {
            delete = AER.findById(enemyID).get();

            delete.setDeleted(true);
        }catch(NoSuchElementException ex) {
            throw new NoSuchElementException("Achievement " + enemyID + " does not exist!");
        }finally {
            return AER.save(delete);
        }
    }
}
