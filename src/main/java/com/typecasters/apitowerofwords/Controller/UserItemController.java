package com.typecasters.apitowerofwords.Controller;

import com.typecasters.apitowerofwords.Entity.ItemEntity;
import com.typecasters.apitowerofwords.Entity.UserItemEntity;
import com.typecasters.apitowerofwords.Service.UserDetailsService;
import com.typecasters.apitowerofwords.Service.UserItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public UserItemEntity insertUserItem(@RequestBody UserItemEntity user_item){
        return userItemService.insertUserItem(user_item);
    }

    //For Inventory
    @GetMapping("/get_user_items_by/{userId}")
    public List<UserItemEntity> getUserItemById(@PathVariable int userId) {
        return userItemService.getUserItemByUserId(userId);
    }

    //Buy Item
    @PostMapping("/buy_item/{userId}/{itemId}/{itemQuantity}")
    public String buyItem(@PathVariable int userId, @PathVariable int itemId, @PathVariable int itemQuantity) {
        return userItemService.buyItem(userId, itemId, itemQuantity);
    }

    @GetMapping("/get_user_item_id_by/{userId}/{itemId}")
    public Optional<Integer> getUserItemIdByUserIdAndItemId(@PathVariable int userId,@PathVariable int itemId){
        return userItemService.getUserItemIdByUserIdAndItemId(userId, itemId);
    }


    //Read
    @GetMapping("/get_all_user_item")
    public List<UserItemEntity> getAllUserItem() {
        return userItemService.getAllUserItem();
    }

    //Update
    @PutMapping("/update_user_item/{user_item_id}")
    public UserItemEntity updateUserItem(@PathVariable int user_item_id, @RequestBody UserItemEntity new_user_item_details){
        return userItemService.updateUserItem(user_item_id, new_user_item_details);
    }

    //Delete
    @DeleteMapping("/delete_user_item/{user_item_id}")
    public String deleteUserItem(@PathVariable int user_item_id){
        return userItemService.deleteUserItem(user_item_id);
    }



}
