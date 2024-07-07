package typecasters.tower_of_words.Controller;

import typecasters.tower_of_words.Entity.AdventureRewardEntity;
import typecasters.tower_of_words.Exception.AdventureRewardNotFound;
import typecasters.tower_of_words.Exception.InsufficientCreditException;
import typecasters.tower_of_words.Exception.ItemQuantityUpdateException;
import typecasters.tower_of_words.Response.NoDataResponse;
import typecasters.tower_of_words.Response.Response;
import typecasters.tower_of_words.Service.AdventureRewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/adventure_reward")
public class AdventureRewardController {

    @Autowired
    private AdventureRewardService ar_serv;

    @PostMapping("/add_reward")
    public ResponseEntity<Object> insertReward(@RequestBody AdventureRewardEntity reward) {
        try {
            AdventureRewardEntity createdReward = ar_serv.insertReward(reward);
            return Response.response(HttpStatus.OK, "Reward added successfully", createdReward);
        } catch (Exception e) {
            return createErrorResponse(e);
        }
    }

    @GetMapping("/get_all_reward")
    public ResponseEntity<Object> getAllReward() {
        try {
            List<AdventureRewardEntity> rewards = ar_serv.getAllReward();
            return Response.response(HttpStatus.OK, "All rewards retrieved successfully", rewards);
        } catch (Exception e) {
            return createErrorResponse(e);
        }
    }

    @GetMapping("/get_reward_by_floor_id")
    public ResponseEntity<Object> getRewardByTowerFloor(@RequestParam int floor_id) {
        try {
            Optional<AdventureRewardEntity> reward = ar_serv.getRewardByTowerFloorId(floor_id);
            return Response.response(HttpStatus.OK, "Reward retrieved successfully", reward);
        } catch (Exception e) {
            return createErrorResponse(e);
        }
    }

    @PutMapping("/update_reward")
    public ResponseEntity<Object> updateReward(@RequestParam int reward_id, @RequestBody AdventureRewardEntity newRewardDetail) {
        try {
            AdventureRewardEntity updatedReward = ar_serv.updateReward(newRewardDetail, reward_id);
            return Response.response(HttpStatus.OK, "Reward updated successfully", updatedReward);
        } catch (NoSuchElementException e) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Reward not found");
        } catch (Exception e) {
            return createErrorResponse(e);
        }
    }

    @DeleteMapping("/delete_reward")
    public ResponseEntity<Object> deleteReward(@RequestParam int rewardId) {
        try {
            String message = ar_serv.deleteReward(rewardId);
            return NoDataResponse.noDataResponse(HttpStatus.OK, message);
        } catch (Exception e) {
            return createErrorResponse(e);
        }
    }

    @PatchMapping("/give_reward_to_user/{floorId}/{userId}")
    public ResponseEntity<Object> updateUserCreditANDUserItem(@PathVariable int floorId, @PathVariable int userId) {
        try {
            String message = ar_serv.updateUserCreditANDUserItem(floorId, userId);
            return NoDataResponse.noDataResponse(HttpStatus.OK, message);
        } catch (Exception e) {
            return createErrorResponse(e);
        }
    }

    private ResponseEntity<Object> createErrorResponse(Exception e) {
        if (e instanceof AdventureRewardNotFound) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } else if (e instanceof InsufficientCreditException) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } else if (e instanceof ItemQuantityUpdateException) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } else {
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
//    // The <?> is a wildcard / unknown type, it actually just means it can return any type, usually
//    // does not restrict what type the ResponseEntity that will return
//
//    // When to use? It is useful when returning a response body is not known at all, can actually provide
//    // flexibility in defining methods, that handles various type of response
//    @PostMapping("/add_reward")
//    public ResponseEntity<?> insertReward(@RequestBody AdventureRewardEntity reward) {
//        try {
//            AdventureRewardEntity createdReward = ar_serv.insertReward(reward);
//            return ResponseEntity.ok(createdReward);
//        } catch (Exception e) {
//            return createErrorResponse(e);
//        }
//    }
//
//    @GetMapping("/get_all_reward")
//    public ResponseEntity<?> getAllReward() {
//        try {
//            List<AdventureRewardEntity> rewards = ar_serv.getAllReward();
//            return ResponseEntity.ok(rewards);
//        } catch (Exception e) {
//            return createErrorResponse(e);
//        }
//    }
//
//    @GetMapping("/get_reward_by_floor_id")
//    public ResponseEntity<?> getRewardByTowerFloor(@RequestParam int floor_id) {
//        try {
//            Optional<AdventureRewardEntity> reward = ar_serv.getRewardByTowerFloorId(floor_id);
//            return ResponseEntity.ok(reward);
//        } catch (Exception e) {
//            return createErrorResponse(e);
//        }
//    }
//
//    @PutMapping("/update_reward")
//    public ResponseEntity<?> updateReward(@RequestParam int reward_id, @RequestBody AdventureRewardEntity newRewardDetail) {
//        try {
//            AdventureRewardEntity updatedReward = ar_serv.updateReward(newRewardDetail, reward_id);
//            return ResponseEntity.ok(updatedReward);
//        } catch (NoSuchElementException e) {
//            return createErrorResponse(String.valueOf(e), HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            return createErrorResponse(e);
//        }
//    }
//
//    @DeleteMapping("/delete_reward")
//    public ResponseEntity<?> deleteReward(@RequestParam int rewardId) {
//        try {
//            String message = ar_serv.deleteReward(rewardId);
//            Map<String, String> response = new HashMap<>();
//            response.put("message", message);
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            return createErrorResponse(e);
//        }
//    }
//
//    @PutMapping("/give_reward_to_user/{floorId}/{userId}")
//    public ResponseEntity<?> updateUserCreditANDUserItem(@PathVariable int floorId, @PathVariable int userId) {
//        try {
//            String message = ar_serv.updateUserCreditANDUserItem(floorId, userId);
//            Map<String, String> response = new HashMap<>();
//            response.put("message", message);
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            return createErrorResponse(e);
//        }
//    }
//
//
//    // The methods below is a helper function, that helps construct a ResponseEntity to return a structured error response
//    // in a JSON Format whenever an exception occurs.
//
//    //The instanceof also checks for e if ever it is a custom Exception if not from above, it will return INTERNAL SERVER ERROR
//    // or 500
//
//    //As for the type of method below, it justs insidcates that the method will be returning a Map<String, String>
//    // It is just making it that both value is String, and returns a simple-key vlaue
//
//    //So below, we will assume that the method below will be returning a JSON Object, where the key is a String
//    private ResponseEntity<Map<String, String>> createErrorResponse(Exception e) {
//        if (e instanceof AdventureRewardNotFound) {
//            return createErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
//        } else if (e instanceof InsufficientCreditException) {
//            return createErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
//        } else if (e instanceof ItemQuantityUpdateException) {
//            return createErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
//        } else {
//            return createErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    // As for the HashMap, it is used to store the error messages, it ensures the response body is a JSON Object,
//    // along with the correct HTTP Status code being returned back to the user or developer
//
//    //So yeah, every response or returned messages are structured as a JSOn Object
//    private ResponseEntity<Map<String, String>> createErrorResponse(String message, HttpStatus status) {
//        Map<String, String> response = new HashMap<>();
//        response.put("message", message);
//        return new ResponseEntity<>(response, status);
//    }
}
