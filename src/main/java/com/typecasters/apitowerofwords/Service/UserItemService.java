package com.typecasters.apitowerofwords.Service;

import com.typecasters.apitowerofwords.Entity.ItemEntity;
import com.typecasters.apitowerofwords.Entity.UserDetailsEntity;
import com.typecasters.apitowerofwords.Entity.UserItemEntity;
import com.typecasters.apitowerofwords.Repository.UserItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserItemService {

    @Autowired
    UserItemRepository userItemRepository;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    ItemService itemService;

    public UserItemEntity insertUserItem(UserItemEntity userItem){
        return userItemRepository.save(userItem);
    }

    public List<UserItemEntity> getAllUserItem(){
        return userItemRepository.findAll();
    }

    public Optional<UserItemEntity> getUserItemById(int userItemId) {
        return userItemRepository.findById(userItemId);
    }

    public Optional<Integer> getUserItemIdByUserIdAndItemId(int userId, int itemId){
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setItemId(itemId);
        return userItemRepository.findUserItemIdByUserIdAndItemId(userId, itemEntity);
    }

    public List<UserItemEntity> getUserItemByUserId(int userId) {
        return userItemRepository.findAllByUserId(userId);
    }

    @SuppressWarnings({"finally", "ReturnInsideFinallyBlock"})
    public UserItemEntity updateUserItem(int userItemId, UserItemEntity newUserItemDetails){

        UserItemEntity user_item = new UserItemEntity();

        try {
            user_item = userItemRepository.findById(userItemId).get();

            user_item.setQuantity(newUserItemDetails.getQuantity());
        } catch (NoSuchElementException ex){
            throw new NoSuchElementException ("User Item " + userItemId + " does not exist.");
        }finally {
            return  userItemRepository.save(user_item);
        }
    }

    public String deleteUserItem(int user_item_id){
        String msg = "";

        if(userItemRepository.findById(user_item_id).isPresent()){
            userItemRepository.deleteById(user_item_id);
            msg = "Item "+ user_item_id + "is successfully deleted!";
        }

        return msg;
    }

    public String buyItem(int userId, int itemId, int itemQuantity) {
        UserDetailsEntity userDetails = userDetailsService.getUserDetails(userId);
        ItemEntity item = itemService.getItem(itemId);
        int totalAmountOfItemPurchased = item.getItem_price() * itemQuantity;
        String msg = "";

        // Stores the UserItemId when it find a correct ID
        Optional<Integer> existingUserItemId = getUserItemIdByUserIdAndItemId(userId,itemId);

        //Checks if the userItemId exist in the database
        if (existingUserItemId.isPresent()) {
            //Stores the Integer UserItemId to int
            int convertedUserItemId = existingUserItemId.get();
            //Stores the object of the UserItemId (it returns the whole UserItem Object of that UserItemId)
            Optional<UserItemEntity> existingUserItemObject = getUserItemById(convertedUserItemId);

            //Checks again if the User Item Object exist
            if(existingUserItemObject.isPresent()){

                //Stores the Object inside this Object variable
                UserItemEntity existingUserItem = existingUserItemObject.get();


                if(userDetails.getCredit_amount() >= totalAmountOfItemPurchased) {
                    userDetailsService.updateUserCredit(userDetails.getUser_detail_id(), -(totalAmountOfItemPurchased));
                    int newQuantity = existingUserItem.getQuantity() + itemQuantity;
                    existingUserItem.setQuantity(newQuantity);
                    userItemRepository.saveAndFlush(existingUserItem);
                    msg = "Item quantity updated successfully.";
                }else{
                    msg = "Insufficient credit to buy this item.";
                }

            }
        } else {

            UserItemEntity userItem = new UserItemEntity(itemQuantity, userId, item);

            if (userDetails.getCredit_amount() >= (totalAmountOfItemPurchased)) {
                userDetailsService.updateUserCredit(userDetails.getUser_detail_id(), -(totalAmountOfItemPurchased));
                insertUserItem(userItem);
                msg = "Item purchased successfully.";
            } else {
                msg = "Insufficient credit to buy this item.";
            }
        }

        return msg;
    }



}
