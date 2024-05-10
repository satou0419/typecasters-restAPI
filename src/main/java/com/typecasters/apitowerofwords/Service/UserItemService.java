package com.typecasters.apitowerofwords.Service;

import com.typecasters.apitowerofwords.Entity.ItemEntity;
import com.typecasters.apitowerofwords.Entity.UserDetailsEntity;
import com.typecasters.apitowerofwords.Entity.UserItemEntity;
import com.typecasters.apitowerofwords.Exception.InsufficientCreditException;
import com.typecasters.apitowerofwords.Exception.InvalidItemQuantityException;
import com.typecasters.apitowerofwords.Repository.UserItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public Optional <UserItemEntity> getUserItemByUserIdAndItemId(int userId, int itemId){
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setItemId(itemId);
        return userItemRepository.findOneByUserIdAndItemId(userId, itemEntity);
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

    @Transactional
    public String buyItem(int userId, int itemId, int itemQuantity) {
        UserDetailsEntity userDetails = null;
        ItemEntity item = null;
        String msg = "";

        try {
            userDetails = userDetailsService.getUserDetails(userId);
            item = itemService.getItem(itemId);
            int totalAmountOfItemPurchased = getTotalAmountOfItemPurchased(itemQuantity, item, userDetails);
            // Fetch existing user item
            Optional<UserItemEntity> existingUserItemObject = getUserItemByUserIdAndItemId(userId, itemId);

            // Update user credit
            userDetailsService.updateUserCredit(userDetails.getUser_detail_id(), -(totalAmountOfItemPurchased));

            // If user item exists, update quantity
            if (existingUserItemObject.isPresent()) {
                UserItemEntity existingUserItem = existingUserItemObject.get();
                int newQuantity = existingUserItem.getQuantity() + itemQuantity;
                existingUserItem.setQuantity(newQuantity);
                userItemRepository.saveAndFlush(existingUserItem);
            } else {
                // Insert new user item
                UserItemEntity userItem = new UserItemEntity(itemQuantity, userId, item);
                insertUserItem(userItem);
            }

            msg = "Item bought successfully";

        } catch (InvalidItemQuantityException | InsufficientCreditException e) {
            msg = e.getMessage();
        } catch (Exception e) {
            msg = "An error occurred while processing the request.";
            e.printStackTrace();
        }

        return msg;
    }

    private static int getTotalAmountOfItemPurchased(int itemQuantity, ItemEntity item, UserDetailsEntity userDetails) {
        int totalAmountOfItemPurchased = item.getItem_price() * itemQuantity;

        //Check if item quantity is valid
        if (itemQuantity <= 0) {
            throw new InvalidItemQuantityException("The item cannot be used when quantity is 0 or below.");
        }

        //Check if user has sufficient credit
        if (userDetails.getCredit_amount() < totalAmountOfItemPurchased) {
            throw new InsufficientCreditException("Insufficient credit to buy this item.");
        }
        return totalAmountOfItemPurchased;
    }


    public String useUserItem(int userId, int itemId){
        Optional<UserItemEntity> existingUserItemObject = getUserItemByUserIdAndItemId(userId, itemId);
        if(existingUserItemObject.isPresent()){

            UserItemEntity existingUserItem = existingUserItemObject.get();
            int userItemQuantity = existingUserItem.getQuantity();
            if(userItemQuantity > 0){

                existingUserItem.setQuantity(userItemQuantity - 1);
                userItemRepository.saveAndFlush(existingUserItem);
                return "The item is successfully used!";
            } else {
                throw new InvalidItemQuantityException("The item cannot be used when quantity is 0 or below.");
            }
        } else {
            throw new NoSuchElementException("User Item does not exist.");
        }
    }

}
