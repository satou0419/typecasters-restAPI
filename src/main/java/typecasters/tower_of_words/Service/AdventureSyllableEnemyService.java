package typecasters.tower_of_words.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import typecasters.tower_of_words.Entity.AdventureSyllableEnemyEntity;
import typecasters.tower_of_words.Repository.AdventureSyllableEnemyRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AdventureSyllableEnemyService {

    @Autowired
    private AdventureSyllableEnemyRepository adventureSyllableEnemyRepository;

    public AdventureSyllableEnemyEntity insertSyllableEnemy(AdventureSyllableEnemyEntity enemy){
        return  adventureSyllableEnemyRepository.save(enemy);
    }

    public List<AdventureSyllableEnemyEntity> getAllSyllableEnemy(){
        return adventureSyllableEnemyRepository.findAll();
    }

    public Optional<AdventureSyllableEnemyEntity> getSyllableEnemyById(int syllableEnemyID){
        return adventureSyllableEnemyRepository.findById(syllableEnemyID);
    }

    public Optional<List<AdventureSyllableEnemyEntity>> getAllSyllableEnemyByFloorID(int towerFloorID){
        return adventureSyllableEnemyRepository.findAllByTowerFloorID(towerFloorID);
    }

    public AdventureSyllableEnemyEntity updateSyllableEnemy(int syllableEnemyID, AdventureSyllableEnemyEntity newSyllableEnemyDetails){
        AdventureSyllableEnemyEntity updatedSyllableEnemy = getSyllableEnemyById(syllableEnemyID)
                .orElseThrow(() -> new NoSuchElementException("Adventure Syllable Enemy ID # " + syllableEnemyID + " doesn't exist!" ));

        updatedSyllableEnemy.setImagePath(newSyllableEnemyDetails.getImagePath());
        updatedSyllableEnemy.setTowerFloorID(newSyllableEnemyDetails.getTowerFloorID());
        updatedSyllableEnemy.setWords(newSyllableEnemyDetails.getWords());

        return adventureSyllableEnemyRepository.save(updatedSyllableEnemy);
    }

    public void deleteSyllableEnemy(int syllableEnemyID){
        AdventureSyllableEnemyEntity updatedSyllableEnemy = getSyllableEnemyById(syllableEnemyID)
                .orElseThrow(() -> new NoSuchElementException("Adventure Syllable Enemy ID # " + syllableEnemyID + " doesn't exist!" ));

        adventureSyllableEnemyRepository.deleteById(updatedSyllableEnemy.getAdventureEnemyID());
    }
}
