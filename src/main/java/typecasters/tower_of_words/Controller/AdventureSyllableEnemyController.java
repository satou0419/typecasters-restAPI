package typecasters.tower_of_words.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import typecasters.tower_of_words.Entity.AdventureSyllableEnemyEntity;
import typecasters.tower_of_words.Response.NoDataResponse;
import typecasters.tower_of_words.Response.Response;
import typecasters.tower_of_words.Service.AdventureSyllableEnemyService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/adventure_syllable_enemy")
public class AdventureSyllableEnemyController {

    @Autowired
    private AdventureSyllableEnemyService adventureSyllableEnemyService;

    @PostMapping("/add_syllable_enemy")
    public ResponseEntity<Object> insertSyllableEnemy(@RequestBody AdventureSyllableEnemyEntity enemy) {
        try {
            AdventureSyllableEnemyEntity createdEnemy = adventureSyllableEnemyService.insertSyllableEnemy(enemy);
            return Response.response(HttpStatus.OK, "Syllable enemy added successfully", createdEnemy);
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get_all_syllable_enemies")
    public ResponseEntity<Object> getAllSyllableEnemies() {
        try {
            List<AdventureSyllableEnemyEntity> enemies = adventureSyllableEnemyService.getAllSyllableEnemy();
            return Response.response(HttpStatus.OK, "Syllable enemies retrieved successfully", enemies);
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get_syllable_enemy_by_id")
    public ResponseEntity<Object> getSyllableEnemyById(@RequestParam int syllableEnemyID) {
        try {
            Optional<AdventureSyllableEnemyEntity> enemy = adventureSyllableEnemyService.getSyllableEnemyById(syllableEnemyID);
            return Response.response(HttpStatus.OK, "Syllable enemy retrieved successfully", enemy);
        } catch (NoSuchElementException e) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get_syllable_enemies_by_floor_id")
    public ResponseEntity<Object> getAllSyllableEnemiesByFloorID(@RequestParam int towerFloorID) {
        try {
            Optional<List<AdventureSyllableEnemyEntity>> enemies = adventureSyllableEnemyService.getAllSyllableEnemyByFloorID(towerFloorID);
            return Response.response(HttpStatus.OK, "Syllable enemies retrieved successfully using floor ID " + towerFloorID + ".", enemies);
        } catch (NoSuchElementException e) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/update_syllable_enemy")
    public ResponseEntity<Object> updateSyllableEnemy(@RequestParam int syllableEnemyID, @RequestBody AdventureSyllableEnemyEntity newSyllableEnemyDetails) {
        try {
            AdventureSyllableEnemyEntity updatedEnemy = adventureSyllableEnemyService.updateSyllableEnemy(syllableEnemyID, newSyllableEnemyDetails);
            return Response.response(HttpStatus.OK, "Syllable enemy updated successfully!", updatedEnemy);
        } catch (NoSuchElementException e) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/delete_syllable_enemy")
    public ResponseEntity<Object> deleteSyllableEnemy(@RequestParam int syllableEnemyID) {
        try {
            adventureSyllableEnemyService.deleteSyllableEnemy(syllableEnemyID);
            return NoDataResponse.noDataResponse(HttpStatus.OK, "Syllable enemy deleted successfully!");
        } catch (NoSuchElementException e) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
