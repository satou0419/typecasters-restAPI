package typecasters.tower_of_words.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import typecasters.tower_of_words.Entity.TowerSilentFloorEntity;
import typecasters.tower_of_words.Repository.TowerSilentFloorRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TowerSilentFloorService {

    @Autowired
    TowerSilentFloorRepository towerSilentFloorRepository;

    public TowerSilentFloorEntity createSilentFloor(TowerSilentFloorEntity silentFloor){
        return towerSilentFloorRepository.save(silentFloor);
    }

    public List<TowerSilentFloorEntity> getAllSilentFloors(){
        return towerSilentFloorRepository.findAll();
    }

    public Optional<TowerSilentFloorEntity> getSilentFloorByID(int silentFloorID){
        return towerSilentFloorRepository.findById(silentFloorID);
    }

    public TowerSilentFloorEntity updateSilentFloor(int silentFloorID, TowerSilentFloorEntity newSilentFloorDetails){
        TowerSilentFloorEntity silentFloor = getSilentFloorByID(silentFloorID)
                .orElseThrow(() -> new NoSuchElementException("Tower Silent Floor ID #" + silentFloorID + "doesn't exist!"));

        silentFloor.setSectionFloor(newSilentFloorDetails.getSectionFloor());
        silentFloor.setTowerSection(newSilentFloorDetails.getTowerSection());

        return towerSilentFloorRepository.save(silentFloor);
    }

    public void deleteSilentFloor(int silentFloorID){
        TowerSilentFloorEntity silentFloor = getSilentFloorByID(silentFloorID)
                .orElseThrow(() -> new NoSuchElementException("Tower Silent Floor ID #" + silentFloorID + "doesn't exist!"));

        towerSilentFloorRepository.deleteById(silentFloor.getTowerFloorID());
    }


}
