package typecasters.tower_of_words.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import typecasters.tower_of_words.Entity.AdventureSilentEnemyEntity;
import typecasters.tower_of_words.Response.NoDataResponse;
import typecasters.tower_of_words.Response.Response;
import typecasters.tower_of_words.Service.AdventureSilentEnemyService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/adventure_silent_enemy")
public class AdventureSilentEnemyController {

    @Autowired
    private AdventureSilentEnemyService adventureSilentEnemyService;

    @PostMapping("/add_silent_enemy")
    public ResponseEntity<Object> insertSilentEnemy(@RequestBody AdventureSilentEnemyEntity enemy) {
        try {
            AdventureSilentEnemyEntity createdEnemy = adventureSilentEnemyService.insertSilentEnemy(enemy);
            return Response.response(HttpStatus.OK, "Silent enemy added successfully", createdEnemy);
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get_all_silent_enemies")
    public ResponseEntity<Object> getAllSilentEnemies() {
        try {
            List<AdventureSilentEnemyEntity> enemies = adventureSilentEnemyService.getAllSilentEnemy();
            return Response.response(HttpStatus.OK, "Silent enemies retrieved successfully", enemies);
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get_silent_enemy_by_id")
    public ResponseEntity<Object> getSilentEnemyById(@RequestParam int silentEnemyID) {
        try {
            Optional<AdventureSilentEnemyEntity> enemy = adventureSilentEnemyService.getSilentEnemyById(silentEnemyID);
            if (enemy.isPresent()) {
                return Response.response(HttpStatus.OK, "Silent enemy retrieved successfully", enemy);
            } else {
                return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Silent enemy ID# " + silentEnemyID + " not found.");
            }
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get_silent_enemies_by_floor_id")
    public ResponseEntity<Object> getAllSilentEnemiesByFloorID(@RequestParam int towerFloorID) {
        try {
            Optional<List<AdventureSilentEnemyEntity>> enemies = adventureSilentEnemyService.getAllSilentEnemyByFloorID(towerFloorID);
            if (enemies.isPresent()) {
                return Response.response(HttpStatus.OK, "Silent enemies retrieved successfully using floor ID " + towerFloorID + ".", enemies);
            } else {
                return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "No silent enemies found for floor ID " + towerFloorID + ".");
            }
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/update_silent_enemy")
    public ResponseEntity<Object> updateSilentEnemy(@RequestParam int silentEnemyID, @RequestBody AdventureSilentEnemyEntity newSilentEnemyDetails) {
        try {
            AdventureSilentEnemyEntity updatedEnemy = adventureSilentEnemyService.updateSilentEnemy(silentEnemyID, newSilentEnemyDetails);
            return Response.response(HttpStatus.OK, "Silent enemy updated successfully!", updatedEnemy);
        } catch (NoSuchElementException e) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/delete_silent_enemy")
    public ResponseEntity<Object> deleteSilentEnemy(@RequestParam int silentEnemyID) {
        try {
            adventureSilentEnemyService.deleteSilentEnemy(silentEnemyID);
            return NoDataResponse.noDataResponse(HttpStatus.OK, "Silent enemy deleted successfully!");
        } catch (NoSuchElementException e) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
