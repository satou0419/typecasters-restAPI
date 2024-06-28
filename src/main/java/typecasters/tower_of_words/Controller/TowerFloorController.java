package typecasters.tower_of_words.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import typecasters.tower_of_words.Entity.TowerFloorEntity;
import typecasters.tower_of_words.Response.NoDataResponse;
import typecasters.tower_of_words.Response.Response;
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

    // Insert
    @PostMapping("/add_floor")
    public ResponseEntity<Object> insertFloor(@RequestBody TowerFloorEntity floor) {
        try {
            TowerFloorEntity insertedFloor = tf_serv.createFloor(floor);
            return Response.response(HttpStatus.OK, "Floor inserted successfully", insertedFloor);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    // Read all
    @GetMapping("/get_all_floors")
    public ResponseEntity<Object> getAllFloors() {
        try {
            List<TowerFloorEntity> floors = tf_serv.getAllFloors();
            return Response.response(HttpStatus.OK, "All floors retrieved successfully", floors);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    // Read One
    @GetMapping("/get_floor_by_id/{floor_id}")
    public ResponseEntity<Object> getFloorById(@PathVariable int floor_id) {
        try {
            Optional<TowerFloorEntity> floor = tf_serv.getFloorById(floor_id);
            return floor.map(value -> Response.response(HttpStatus.OK, "Floor retrieved successfully", value))
                    .orElseGet(() -> NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Floor not found"));
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    // Update
    @PutMapping("/update_floor/{floor_id}")
    public ResponseEntity<Object> updateFloor(@PathVariable int floor_id, @RequestBody TowerFloorEntity newFloorDetails) {
        try {
            TowerFloorEntity updatedFloor = tf_serv.updateFloor(floor_id, newFloorDetails);
            return Response.response(HttpStatus.OK, "Floor updated successfully", updatedFloor);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    // Delete
    @DeleteMapping("/delete_floor/{floor_id}")
    public ResponseEntity<Object> deleteFloor(@PathVariable int floor_id) {
        try {
            String result = tf_serv.deleteFloor(floor_id);
            return NoDataResponse.noDataResponse(HttpStatus.OK, result);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

}
