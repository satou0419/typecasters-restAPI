package typecasters.tower_of_words.Controller;

import typecasters.tower_of_words.Entity.TowerFloorEntity;
import typecasters.tower_of_words.Service.TowerFloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/floor")
public class TowerFloorController {
    @Autowired
    private TowerFloorService tf_serv;

    //Insert
    @PostMapping("/add_floor")
    public TowerFloorEntity insertFloor(@RequestBody TowerFloorEntity floor){
        return tf_serv.createFloor(floor);
    }

    //Read all
    @GetMapping("/get_all_floors")
    public List<TowerFloorEntity> getAllFloors(){
        return tf_serv.getAllFloors();
    }

    //Read One
    @GetMapping("/get_floor_by_id/{floor_id}")
    public Optional<TowerFloorEntity> getFloorById(@PathVariable int floor_id){
        return tf_serv.getFloorById(floor_id);
    }

    //Update
    @PutMapping("/update_floor/{floor_id}")
    public TowerFloorEntity updateFloor(@PathVariable int floor_id, @RequestBody TowerFloorEntity newFoorDetails){
        return tf_serv.updateFloor(floor_id, newFoorDetails);
    }

    //Delete
    @DeleteMapping("delete_floor/{floor_id}")
    public String deleteFloor(@PathVariable int floor_id){
        return tf_serv.deleteFloor(floor_id);
    }

}
