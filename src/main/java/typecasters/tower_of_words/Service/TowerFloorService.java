package typecasters.tower_of_words.Service;

import typecasters.tower_of_words.Entity.TowerFloorEntity;
import typecasters.tower_of_words.Repository.TowerFloorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TowerFloorService {

    @Autowired
    TowerFloorRepository towerFloorRepository;

    //Create
    public TowerFloorEntity createFloor(TowerFloorEntity towerFloor){
        return towerFloorRepository.save(towerFloor);
    }

    //Read
    public List<TowerFloorEntity> getAllFloors(){
        return towerFloorRepository.findAll();
    }

    public Optional<TowerFloorEntity> getFloorById(int floor_id){
        return towerFloorRepository.findById(floor_id);
    }

    //Update
    @SuppressWarnings({"finally", "ReturnInsideFinallyBlock"})
    public TowerFloorEntity updateFloor(int floor_id, TowerFloorEntity newFloorDetails){
        TowerFloorEntity floor = new TowerFloorEntity();

        try{
            floor = towerFloorRepository.findById(floor_id).get();

            floor.setSectionFloor(newFloorDetails.getSectionFloor());
            floor.setTowerSection(newFloorDetails.getTowerSection());
        }catch (NoSuchElementException ex){
            throw new NoSuchElementException("Floor " + floor_id + " does not exist");
        }finally {
            return towerFloorRepository.save(floor);
        }
    }

    //Delete
    public String deleteFloor(int floor_id){
        String msg = "Floor id does not exist";

        if(towerFloorRepository.findById(floor_id).isPresent()){
            towerFloorRepository.deleteById(floor_id);

            msg = "Floor " + floor_id + " is successfully deleted";
        }

        return msg;
    }

}
