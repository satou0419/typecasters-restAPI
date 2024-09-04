package typecasters.tower_of_words.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import typecasters.tower_of_words.Entity.TowerSyllableFloorEntity;
import typecasters.tower_of_words.Response.NoDataResponse;
import typecasters.tower_of_words.Response.Response;
import typecasters.tower_of_words.Service.TowerSyllableFloorService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/syllable_floor")
public class TowerSyllableFloorController {

    @Autowired
    private TowerSyllableFloorService towerSyllableFloorService;

    @PostMapping("/add_syllable_floor")
    public ResponseEntity<Object> insertSyllableFloor(@RequestBody TowerSyllableFloorEntity syllableFloor){
        try{
            TowerSyllableFloorEntity insertedSyllableFloor = towerSyllableFloorService.createSyllableFloor(syllableFloor);
            return Response.response(HttpStatus.OK, "Syllable Floor inserted successfully!", insertedSyllableFloor);
        } catch (IllegalArgumentException ex){
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (Exception ex){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @GetMapping("/get_all_syllable_floors")
    public ResponseEntity<Object> getAllSyllableFloors(){
        try{
            List<TowerSyllableFloorEntity> syllableFloors = towerSyllableFloorService.getAllSyllableFloors();
            return Response.response(HttpStatus.OK, "All syllable floors retrieved successfully!", syllableFloors);
        } catch (IllegalArgumentException ex){
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (Exception ex){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @GetMapping("/get_syllable_floor_by/{syllableFloorID}")
    public ResponseEntity<Object> getSyllableFloorByID(@PathVariable int syllableFloorID){
        try{
            Optional<TowerSyllableFloorEntity> syllableFloor = towerSyllableFloorService.getSyllableFloorByID(syllableFloorID);
            return syllableFloor.map(value -> Response.response(HttpStatus.OK, "Syllable Floor retrieved successfully!", syllableFloor))
                    .orElseGet(() -> NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Syllable Floor ID # " + syllableFloorID + " not found!"));
        } catch (IllegalArgumentException ex){
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (Exception ex){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @PatchMapping("/update_syllable_floor_by/{syllableFloorID}")
    public ResponseEntity<Object> updateSyllableFloor(@PathVariable int syllableFloorID, @RequestBody TowerSyllableFloorEntity newSyllableFloorDetails){
        try{
            TowerSyllableFloorEntity updatedSyllableFloor = towerSyllableFloorService.updateSyllableFloor(syllableFloorID, newSyllableFloorDetails);
            return Response.response(HttpStatus.OK, "Syllable Floor updated successfully!", updatedSyllableFloor);
        } catch (NoSuchElementException ex){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (IllegalArgumentException ex){
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (Exception ex){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @DeleteMapping("/delete_syllable_floor_by")
    public ResponseEntity<Object> deleteSyllableFloor(@PathVariable int syllableFloorID){
        try{
            towerSyllableFloorService.deleteSyllableFloor(syllableFloorID);
            return NoDataResponse.noDataResponse(HttpStatus.OK, "Syllable Floor ID #" + syllableFloorID + " has been deleted!");
        } catch (NoSuchElementException ex){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (IllegalArgumentException ex){
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (Exception ex){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
}
