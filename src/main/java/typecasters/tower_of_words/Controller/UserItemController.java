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
    @GetMapping("/get_user_items_by/{userID}")
    public ResponseEntity<Object> getUserItemById(@PathVariable int userID) {
        try {
            List<UserItemEntity> userItems = userItemService.getUserItemByUserId(userID);
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
    @PostMapping("/buy_item_any_amount/{userID}/{itemID}/{itemQuantity}")
    public ResponseEntity<Object> buyItemAnyAmount(@PathVariable int userID, @PathVariable int itemID, @PathVariable int itemQuantity) {
        try {
            String buyResult = userItemService.butItemAnyAmount(userID, itemID, itemQuantity);
            return NoDataResponse.noDataResponse(HttpStatus.OK, buyResult);
        } catch (InvalidItemQuantityException | InsufficientCreditException e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (NoSuchElementException e){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    //Buy Item one at a time
    @PostMapping("/buy_item_single/{userID}/{itemID}")
    public ResponseEntity<Object> buyItemSingle(@PathVariable int userID, @PathVariable int itemID) {
        try {
            String buyResult = userItemService.buyItemSingle(userID, itemID);
            return NoDataResponse.noDataResponse(HttpStatus.OK, buyResult);
        } catch (InsufficientCreditException e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (NoSuchElementException e){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("/use_item/{userID}/{itemID}")
    public ResponseEntity<Object> useUserItem(@PathVariable int userID, @PathVariable int itemID) {
        try {
            String msg = userItemService.useUserItem(userID, itemID);
            return Response.response(HttpStatus.OK, "User item used successfully", msg);
        } catch (InvalidItemQuantityException e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (NoSuchElementException e){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/get_user_item_id_by/{userID}/{itemID}")
    public ResponseEntity<Object> getUserItemIdByUserIdAndItemId(@PathVariable int userID,@PathVariable int itemID){
        try {
            Optional<Integer> userItemID = userItemService.getUserItemIdByUserIdAndItemId(userID, itemID);
            if(userItemID.isPresent()){
                return Response.response(HttpStatus.OK, "User item ID retrieved successfully", userItemID.get());
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
                return Response.response(HttpStatus.OK, "All UserItems Retrieved Succesfully!", allUserItems);
            } else {
                return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "No user items found");
            }
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    //Update
    @PutMapping("/update_user_item/{userItemID}")
    public ResponseEntity<Object> updateUserItem(@PathVariable int userItemID, @RequestBody UserItemEntity newUserItemDetails) {
        try {
            UserItemEntity updatedUserItem = userItemService.updateUserItem(userItemID, newUserItemDetails);
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
    @DeleteMapping("/delete_user_item/{userItemID}")
    public ResponseEntity<Object> deleteUserItem(@PathVariable int userItemID) {
        try {
            String deletionResult = userItemService.deleteUserItem(userItemID);
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
