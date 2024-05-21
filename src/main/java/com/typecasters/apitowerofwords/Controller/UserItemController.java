package com.typecasters.apitowerofwords.Controller;

import com.typecasters.apitowerofwords.Entity.UserItemEntity;
import com.typecasters.apitowerofwords.Exception.InsufficientCreditException;
import com.typecasters.apitowerofwords.Exception.InvalidItemQuantityException;
import com.typecasters.apitowerofwords.Service.UserDetailsService;
import com.typecasters.apitowerofwords.Service.UserItemService;
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
    public ResponseEntity<UserItemEntity> insertUserItem(@RequestBody UserItemEntity userItem){
        UserItemEntity insertedUserItem = userItemService.insertUserItem(userItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(insertedUserItem);
    }

    //For Inventory
    @GetMapping("/get_user_items_by/{userId}")
    public ResponseEntity<List<UserItemEntity>> getUserItemById(@PathVariable int userId) {
        List<UserItemEntity> userItems = userItemService.getUserItemByUserId(userId);
        if(userItems != null && !userItems.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(userItems);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    //Buy Item at Any Amount
    @PostMapping("/buy_item_any_amount/{userId}/{itemId}/{itemQuantity}")
    public ResponseEntity<String> buyItemAnyAmount(@PathVariable int userId, @PathVariable int itemId, @PathVariable int itemQuantity) {
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
    public ResponseEntity<String> buyItemSingle(@PathVariable int userId, @PathVariable int itemId) {
        try {
            String buyResult = userItemService.buyItemSingle(userId, itemId);
            HttpStatus status = buyResult.equals("Item bought successfully") ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
            return ResponseEntity.status(status).body(buyResult);
        } catch (InsufficientCreditException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PutMapping("/use_item/{userId}/{itemId}")
    public ResponseEntity<String> useUserItem(@PathVariable int userId, @PathVariable int itemId) {
        try {
            String msg = userItemService.useUserItem(userId, itemId);
            return ResponseEntity.status(HttpStatus.OK).body(msg);
        } catch (InvalidItemQuantityException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/get_user_item_id_by/{userId}/{itemId}")
    public ResponseEntity<Integer> getUserItemIdByUserIdAndItemId(@PathVariable int userId,@PathVariable int itemId){
        Optional<Integer> userItemId = userItemService.getUserItemIdByUserIdAndItemId(userId, itemId);

        if(userItemId.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(userItemId.get());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //Read
    @GetMapping("/get_all_user_item")
    public ResponseEntity<List<UserItemEntity>> getAllUserItem() {
        List<UserItemEntity> allUserItems = userItemService.getAllUserItem();
        if (allUserItems != null && !allUserItems.isEmpty()) {
            return ResponseEntity.ok().body(allUserItems);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //Update
    @PutMapping("/update_user_item/{user_item_id}")
    public ResponseEntity<UserItemEntity> updateUserItem(@PathVariable int user_item_id, @RequestBody UserItemEntity new_user_item_details) {
        UserItemEntity updatedUserItem = userItemService.updateUserItem(user_item_id, new_user_item_details);
        if (updatedUserItem != null) {
            return ResponseEntity.ok().body(updatedUserItem);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //Delete
    @DeleteMapping("/delete_user_item/{user_item_id}")
    public ResponseEntity<String> deleteUserItem(@PathVariable int user_item_id) {
        String deletionResult = userItemService.deleteUserItem(user_item_id);
        if (deletionResult.equals("User item deleted successfully")) {
            return ResponseEntity.ok().body(deletionResult);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(deletionResult);
        }
    }



}
