package com.typecasters.apitowerofwords.Service;

import com.typecasters.apitowerofwords.Entity.TowerFloorEntity;
import com.typecasters.apitowerofwords.Repository.TowerFloorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TowerFloorService {

    @Autowired
    TowerFloorRepository tf_repo;

    //Create
    public TowerFloorEntity createFloor(TowerFloorEntity towerFloor){
        return tf_repo.save(towerFloor);
    }

    //Read
    public List<TowerFloorEntity> getAllFloors(){
        return tf_repo.findAll();
    }

    public Optional<TowerFloorEntity> getFloorById(int floor_id){
        return tf_repo.findById(floor_id);
    }

    //Update
    @SuppressWarnings({"finally", "ReturnInsideFinallyBlock"})
    public TowerFloorEntity updateFloor(int floor_id, TowerFloorEntity newFloorDetails){
        TowerFloorEntity floor = new TowerFloorEntity();

        try{
            floor = tf_repo.findById(floor_id).get();

            floor.setSectionFloor(newFloorDetails.getSectionFloor());
            floor.setTowerSection(newFloorDetails.getTowerSection());
        }catch (NoSuchElementException ex){
            throw new NoSuchElementException("Floor " + floor_id + " does not exist");
        }finally {
            return tf_repo.save(floor);
        }
    }

    //Delete
    public String deleteFloor(int floor_id){
        String msg = "Floor id does not exist";

        if(tf_repo.findById(floor_id).isPresent()){
            tf_repo.deleteById(floor_id);

            msg = "Floor " + floor_id + " is successfully deleted";
        }

        return msg;
    }

}
