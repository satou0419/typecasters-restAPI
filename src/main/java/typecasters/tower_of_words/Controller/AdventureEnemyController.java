package typecasters.tower_of_words.Controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import typecasters.tower_of_words.Entity.AdventureEnemyEntity;
import typecasters.tower_of_words.Entity.AdventureRewardEntity;
import typecasters.tower_of_words.Entity.ArchiveAchievementEntity;
import typecasters.tower_of_words.Response.NoDataResponse;
import typecasters.tower_of_words.Response.Response;
import typecasters.tower_of_words.Service.AdventureEnemyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/adventure_enemy")
public class AdventureEnemyController {

    @Autowired
    private AdventureEnemyService adventureEnemyService;

    @PostMapping("/add_enemy")
    public ResponseEntity<Object> insertEnemy(@RequestBody AdventureEnemyEntity enemy){
        try {
            AdventureEnemyEntity createdEnemy = adventureEnemyService.insertEnemy(enemy);
            return Response.response(HttpStatus.OK, "Enemy added successfully", createdEnemy);
        } catch (Exception e){
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    //Read All
    @GetMapping("/get_all_enemy")
    public ResponseEntity<Object> getAllEnemy(){
//        return adventureEnemyService.getAllEnemy();

        try{
            List<AdventureEnemyEntity> enemies = adventureEnemyService.getAllEnemy();
            return Response.response(HttpStatus.OK, "Enemies retrieved succesfully", enemies);
        }catch (Exception e){
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    //Read One
    @GetMapping("/get_enemy_by_id")
    public ResponseEntity<Object> getEnemyById(@RequestParam int enemy_id){
//        return adventureEnemyService.getEnemyById(enemy_id);

        try{
            Optional<AdventureEnemyEntity> enemy = adventureEnemyService.getEnemyById(enemy_id);
            return Response.response(HttpStatus.OK, "Enemy retrieved succesfully", enemy);
        }catch (NoSuchElementException e){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
        }catch (Exception e){
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    //Read By foreign key
    @GetMapping("/get_enemy_by_floor_id")
    public ResponseEntity<Object> getEnemyByFloorId(@RequestParam int floor_id){
//        return adventureEnemyService.getAllByFloorId(floor_id);

        try{
            Optional<List<AdventureEnemyEntity>> enemyByFloorId = adventureEnemyService.getAllByFloorId(floor_id);
            return Response.response(HttpStatus.OK, "Enemy retrieved successfully using floor id " + floor_id + ".", enemyByFloorId);
        }catch (NoSuchElementException e){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
        }catch (Exception e){
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    //Update
    @PutMapping("/update_enemy")
    public ResponseEntity<Object> updateEnemy(@RequestParam int enemy_id, @RequestBody AdventureEnemyEntity newEnemyDetails){
//        return adventureEnemyService.updateEnemy(enemy_id, newEnemyDetails);

        try{
            AdventureEnemyEntity updatedEnemy = adventureEnemyService.updateEnemy(enemy_id, newEnemyDetails);
            return Response.response(HttpStatus.OK, "Enemy updated successfully!", updatedEnemy);
        }catch(NoSuchElementException e){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
        }catch (Exception e){
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/delete_enemy")
    public ResponseEntity<Object> deleteEnemy(@RequestParam int enemy_id){
//        return adventureEnemyService.deleteEnemy(enemy_id);
        try{
            String msgFromDeletedEnemy = adventureEnemyService.deleteEnemy(enemy_id);
            return NoDataResponse.noDataResponse(HttpStatus.OK,"Enemy deleted succesfully!");
        }catch(NoSuchElementException e){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
        }catch(Exception e){
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
