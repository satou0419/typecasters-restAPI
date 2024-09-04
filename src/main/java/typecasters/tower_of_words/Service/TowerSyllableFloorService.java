package typecasters.tower_of_words.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import typecasters.tower_of_words.Entity.TowerSilentFloorEntity;
import typecasters.tower_of_words.Entity.TowerSyllableFloorEntity;
import typecasters.tower_of_words.Repository.TowerSyllableFloorRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TowerSyllableFloorService {

    @Autowired
    TowerSyllableFloorRepository towerSyllableFloorRepository;

    public TowerSyllableFloorEntity createSyllableFloor(TowerSyllableFloorEntity syllableFloor){
        return towerSyllableFloorRepository.save(syllableFloor);
    }

    public List<TowerSyllableFloorEntity> getAllSyllableFloors(){
        return towerSyllableFloorRepository.findAll();
    }

    public Optional<TowerSyllableFloorEntity> getSyllableFloorByID(int syllableFloorID){
        return towerSyllableFloorRepository.findById(syllableFloorID);
    }

    public TowerSyllableFloorEntity updateSyllableFloor(int syllableFloorID, TowerSyllableFloorEntity newTowerSyllableFloor){
        TowerSyllableFloorEntity syllableFloor = getSyllableFloorByID(syllableFloorID)
                .orElseThrow(() -> new NoSuchElementException("Tower Syllable Floor ID #" + syllableFloorID + " doesn't exist!"));

        syllableFloor.setSectionFloor(newTowerSyllableFloor.getSectionFloor());
        syllableFloor.setTowerSection(newTowerSyllableFloor.getTowerSection());

        return towerSyllableFloorRepository.save(syllableFloor);
    }

    public void deleteSyllableFloor(int syllableFloorID){
        TowerSyllableFloorEntity syllableFloor = getSyllableFloorByID(syllableFloorID)
                .orElseThrow(() -> new NoSuchElementException("Tower Syllable Floor ID #" + syllableFloorID + " doesn't exist!"));

        towerSyllableFloorRepository.deleteById(syllableFloor.getTowerSyllableFloorID());
    }
}
