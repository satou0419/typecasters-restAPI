package com.typecasters.apitowerofwords.Service;

import com.typecasters.apitowerofwords.Entity.UserItemEntity;
import com.typecasters.apitowerofwords.Repository.UserItemRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserItemService {

    @Autowired
    UserItemRepository user_item_repo;

    public UserItemEntity insertUserItem(UserItemEntity user_item){
        return user_item_repo.save(user_item);
    }

    public List<UserItemEntity> getAllUserItem(){
        return user_item_repo.findAll();
    }

    @SuppressWarnings({"finally", "ReturnInsideFinallyBlock"})
    public UserItemEntity updateUserItem(int user_item_id, UserItemEntity new_user_item_details){

        UserItemEntity user_item = new UserItemEntity();

        try {
            user_item = user_item_repo.findById(user_item_id).get();

            user_item.setQuantity(new_user_item_details.getQuantity());
        } catch (NoSuchElementException ex){
            throw new NoSuchElementException ("User Item " + user_item_id + " does not exist.");
        }finally {
            return  user_item_repo.save(user_item);
        }
    }

    public String deleteUserItem(int user_item_id){
        String msg = "";

        if(user_item_repo.findById(user_item_id).isPresent()){
            user_item_repo.deleteById(user_item_id);
            msg = "Item "+ user_item_id + "is successfully deleted!";
        }

        return msg;
    }

}
