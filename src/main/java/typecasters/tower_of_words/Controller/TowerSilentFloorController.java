package typecasters.tower_of_words.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import typecasters.tower_of_words.Entity.TowerSilentFloorEntity;
import typecasters.tower_of_words.Entity.TowerSyllableFloorEntity;
import typecasters.tower_of_words.Response.NoDataResponse;
import typecasters.tower_of_words.Response.Response;
import typecasters.tower_of_words.Service.TowerSilentFloorService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/silent_floor")
public class TowerSilentFloorController {

    @Autowired
    private TowerSilentFloorService towerSilentFloorService;

    @PostMapping("/add_silent_floor")
    public ResponseEntity<Object> insertSilentFloor(@RequestBody TowerSilentFloorEntity silentFloor){
        try{
            TowerSilentFloorEntity insertedSilentFloor = towerSilentFloorService.createSilentFloor(silentFloor);
            return Response.response(HttpStatus.OK, "Silent Floor inserted successfully!", insertedSilentFloor);
        } catch (IllegalArgumentException ex){
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (Exception ex){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @GetMapping("/get_all_silent_floors")
    public ResponseEntity<Object> getAllSilentFloors(){
        try{
            List<TowerSilentFloorEntity> silentFloors = towerSilentFloorService.getAllSilentFloors();
            return Response.response(HttpStatus.OK, "All silent floors retrieved successfully!", silentFloors);
        } catch (IllegalArgumentException ex){
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (Exception ex){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @GetMapping("/get_silent_floor_by_id/{silentFloorID}")
    public ResponseEntity<Object> getSilentFloorByID(@PathVariable int silentFloorID){
        try{
            Optional<TowerSilentFloorEntity> silentFloor = towerSilentFloorService.getSilentFloorByID(silentFloorID);
            return silentFloor.map(value -> Response.response(HttpStatus.OK, "Silent Floor retrieved successfully!", silentFloor))
                    .orElseGet(() -> NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Silent Floor ID #" + silentFloorID +" not found!"));
        } catch (IllegalArgumentException ex){
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (Exception ex){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @PatchMapping("/update_silent_floor_by/{silentFloorID}")
    public ResponseEntity<Object> updateSilentFloor(@PathVariable int silentFloorID, @RequestBody TowerSilentFloorEntity newSilentFloorDetails){
        try{
            TowerSilentFloorEntity updatedSilentFloor = towerSilentFloorService.updateSilentFloor(silentFloorID, newSilentFloorDetails);
            return Response.response(HttpStatus.OK, "Silent Floor updated successfully!", updatedSilentFloor);
        } catch (NoSuchElementException ex){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (IllegalArgumentException ex){
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (Exception ex){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @DeleteMapping("/delete_silent_floor_by/{silentFloorID}")
    public ResponseEntity<Object> deleteSilentFloor(@PathVariable int silentFloorID){
        try{
            towerSilentFloorService.deleteSilentFloor(silentFloorID);
            return NoDataResponse.noDataResponse(HttpStatus.OK, "Silent Floor ID #" + silentFloorID + " deleted successfully!");
        } catch (NoSuchElementException ex){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (IllegalArgumentException ex){
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (Exception ex){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
}
