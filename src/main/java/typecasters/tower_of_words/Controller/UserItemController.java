package typecasters.tower_of_words.Controller;

import typecasters.tower_of_words.Entity.UserItemEntity;
import typecasters.tower_of_words.Exception.InsufficientCreditException;
import typecasters.tower_of_words.Exception.InvalidItemQuantityException;
import typecasters.tower_of_words.Response.NoDataResponse;
import typecasters.tower_of_words.Response.Response;
import typecasters.tower_of_words.Service.UserDetailsService;
import typecasters.tower_of_words.Service.UserItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/user_item")
public class UserItemController {

    @Autowired
    UserItemService userItemService;

    @Autowired
    UserDetailsService userDetailsService;


    //Create
    @PostMapping("/insert_user_item")
    public ResponseEntity<Object> insertUserItem(@RequestBody UserItemEntity userItem){
        try {
            UserItemEntity insertedUserItem = userItemService.insertUserItem(userItem);
            return Response.response(HttpStatus.CREATED, "User item inserted successfully", insertedUserItem);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    //For Inventory
    @GetMapping("/get_user_items_by/{userId}")
    public ResponseEntity<Object> getUserItemById(@PathVariable int userId) {
        try {
            List<UserItemEntity> userItems = userItemService.getUserItemByUserId(userId);
            if (userItems != null && !userItems.isEmpty()) {
                return Response.response(HttpStatus.OK, "User items retrieved successfully", userItems);
            } else {
                return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "User items not found");
            }
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }


    //Buy Item at Any Amount
    @PostMapping("/buy_item_any_amount/{userId}/{itemId}/{itemQuantity}")
    public ResponseEntity<Object> buyItemAnyAmount(@PathVariable int userId, @PathVariable int itemId, @PathVariable int itemQuantity) {
        try {
            String buyResult = userItemService.butItemAnyAmount(userId, itemId, itemQuantity);
            HttpStatus status = buyResult.equals("Item bought successfully") ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
            return ResponseEntity.status(status).body(buyResult);
        } catch (InvalidItemQuantityException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (InsufficientCreditException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    //Buy Item one at a time
    @PostMapping("/buy_item_single/{userId}/{itemId}")
    public ResponseEntity<Object> buyItemSingle(@PathVariable int userId, @PathVariable int itemId) {
        try {
            String buyResult = userItemService.buyItemSingle(userId, itemId);
            HttpStatus status = buyResult.equals("Item bought successfully") ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
            return ResponseEntity.status(status).body(buyResult);
        } catch (InsufficientCreditException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PutMapping("/use_item/{userId}/{itemId}")
    public ResponseEntity<Object> useUserItem(@PathVariable int userId, @PathVariable int itemId) {
        try {
            String msg = userItemService.useUserItem(userId, itemId);
            return Response.response(HttpStatus.OK, "User item used successfully", msg);
        } catch (InvalidItemQuantityException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (NoSuchElementException e){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/get_user_item_id_by/{userId}/{itemId}")
    public ResponseEntity<Object> getUserItemIdByUserIdAndItemId(@PathVariable int userId,@PathVariable int itemId){
        try {
            Optional<Integer> userItemId = userItemService.getUserItemIdByUserIdAndItemId(userId, itemId);
            if(userItemId.isPresent()){
                return Response.response(HttpStatus.OK, "User item ID retrieved successfully", userItemId.get());
            } else {
                return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "User item ID not found");
            }
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    //Read
    @GetMapping("/get_all_user_item")
    public ResponseEntity<Object> getAllUserItem() {
        try {
            List<UserItemEntity> allUserItems = userItemService.getAllUserItem();
            if (allUserItems != null && !allUserItems.isEmpty()) {
                return ResponseEntity.ok().body(allUserItems);
            } else {
                return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "No user items found");
            }
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    //Update
    @PutMapping("/update_user_item/{user_item_id}")
    public ResponseEntity<Object> updateUserItem(@PathVariable int user_item_id, @RequestBody UserItemEntity new_user_item_details) {
        try {
            UserItemEntity updatedUserItem = userItemService.updateUserItem(user_item_id, new_user_item_details);
            if (updatedUserItem != null) {
                return Response.response(HttpStatus.OK, "User item updated successfully", updatedUserItem);
            } else {
                return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "User item not found");
            }
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    //Delete
    @DeleteMapping("/delete_user_item/{user_item_id}")
    public ResponseEntity<Object> deleteUserItem(@PathVariable int user_item_id) {
        try {
            String deletionResult = userItemService.deleteUserItem(user_item_id);
            if (deletionResult.equals("User item deleted successfully")) {
                return ResponseEntity.ok().body(deletionResult);
            } else {
                return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, deletionResult);
            }
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }



}
