package com.typecasters.apitowerofwords.Controller;

import com.typecasters.apitowerofwords.Entity.UserItemEntity;
import com.typecasters.apitowerofwords.Service.UserItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user_item")
public class UserItemController {

    @Autowired
    UserItemService user_item_service;

    //Create
    @PostMapping("/insert_user_item")
    public UserItemEntity insertUserItem(@RequestBody UserItemEntity user_item){
        return user_item_service.insertUserItem(user_item);
    }

    //Read
    @GetMapping("/get_all_user_item")
    public List<UserItemEntity> getAllUserItem() {
        return user_item_service.getAllUserItem();
    }

    //Update
    @PutMapping("/update_user_item/{user_item_id}")
    public UserItemEntity updateUserItem(@PathVariable int user_item_id, @RequestBody UserItemEntity new_user_item_details){
        return user_item_service.updateUserItem(user_item_id, new_user_item_details);
    }

    //Delete
    @DeleteMapping("/delete_user_item/{user_item_id}")
    public String deleteUserItem(@PathVariable int user_item_id){
        return user_item_service.deleteUserItem(user_item_id);
    }
}
