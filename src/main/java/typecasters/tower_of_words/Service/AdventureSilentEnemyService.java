package typecasters.tower_of_words.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import typecasters.tower_of_words.Entity.AdventureSilentEnemyEntity;
import typecasters.tower_of_words.Repository.AdventureSilentEnemyRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AdventureSilentEnemyService {

    @Autowired
    private AdventureSilentEnemyRepository adventureSilentEnemyRepository;

    public AdventureSilentEnemyEntity insertSilentEnemy(AdventureSilentEnemyEntity enemy){
        return adventureSilentEnemyRepository.save(enemy);
    }

    public List<AdventureSilentEnemyEntity> getAllSilentEnemy(){
        return adventureSilentEnemyRepository.findAll();
    }

    public Optional<AdventureSilentEnemyEntity> getSilentEnemyById(int silentEnemyID){
        return adventureSilentEnemyRepository.findById(silentEnemyID);
    }

    public Optional<List<AdventureSilentEnemyEntity>> getAllSilentEnemyByFloorID(int towerFloorID){
        return adventureSilentEnemyRepository.findAllByTowerFloorID(towerFloorID);
    }

    public AdventureSilentEnemyEntity updateSilentEnemy(int silentEnemyID, AdventureSilentEnemyEntity newSilentEnemyDetails){
        AdventureSilentEnemyEntity updatedSilentEnemy = getSilentEnemyById(silentEnemyID)
                .orElseThrow(() -> new NoSuchElementException("Adventure Silent Enemy ID# " + silentEnemyID + " doesn't exist!"));

        updatedSilentEnemy.setImagePath(newSilentEnemyDetails.getImagePath());
        updatedSilentEnemy.setTowerFloorID(newSilentEnemyDetails.getTowerFloorID());
        updatedSilentEnemy.setWords(newSilentEnemyDetails.getWords());

        return  adventureSilentEnemyRepository.save(updatedSilentEnemy);
    }

    public void deleteSilentEnemy(int silentEnemyID){
        AdventureSilentEnemyEntity updatedSilentEnemy = getSilentEnemyById(silentEnemyID)
                .orElseThrow(() -> new NoSuchElementException("Adventure Silent Enemy ID# " + silentEnemyID + " doesn't exist!"));

        adventureSilentEnemyRepository.deleteById(updatedSilentEnemy.getAdventureSilentEnemyID());
    }
}
