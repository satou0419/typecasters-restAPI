package typecasters.tower_of_words.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import typecasters.tower_of_words.Entity.GameTypeEntity;
import typecasters.tower_of_words.Response.NoDataResponse;
import typecasters.tower_of_words.Response.Response;
import typecasters.tower_of_words.Service.GameTypeService;

import java.util.List;

@RestController
@RequestMapping("/gameType")
public class GameTypeController {
    @Autowired
    GameTypeService gameTypeService;

    @PostMapping("/insert")
    public ResponseEntity<Object> insertGameType(@RequestBody GameTypeEntity gameType) {
        try {
            GameTypeEntity createdGameType = gameTypeService.insertGameType(gameType);
            return Response.response(HttpStatus.CREATED, "GameType created successfully", createdGameType);
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create GameType");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllGameTypes() {
        try {
            List<GameTypeEntity> gameTypes = gameTypeService.getAllGameTypes();
            return Response.response(HttpStatus.OK, "All GameTypes retrieved successfully", gameTypes);
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve GameTypes");
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getGameTypeById(@PathVariable int id) {
        try {
            GameTypeEntity gameType = gameTypeService.getGameType(id);
            return Response.response(HttpStatus.OK, "GameType retrieved successfully", gameType);
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "GameType not found");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateGameType(@PathVariable int id, @RequestBody GameTypeEntity gameType) {
        try {
            GameTypeEntity updatedGameType = gameTypeService.updateGameType(id, gameType);
            return Response.response(HttpStatus.OK, "GameType updated successfully", updatedGameType);
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update GameType");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteGameType(@PathVariable int id) {
        try {
            String message = gameTypeService.deleteGameType(id);
            return Response.response(HttpStatus.OK, message, null);
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete GameType");
        }
    }
}
