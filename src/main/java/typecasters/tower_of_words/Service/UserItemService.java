package typecasters.tower_of_words.Service;

import typecasters.tower_of_words.Entity.ItemEntity;
import typecasters.tower_of_words.Entity.UserDetailsEntity;
import typecasters.tower_of_words.Entity.UserItemEntity;
import typecasters.tower_of_words.Exception.InsufficientCreditException;
import typecasters.tower_of_words.Exception.InvalidItemQuantityException;
import typecasters.tower_of_words.Repository.UserItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
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

    public Optional<Integer> getUserItemIdByUserIdAndItemId(int userID, int itemID){
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setItemID(itemID);
        return userItemRepository.findUserItemIDByUserIDAndItemID(userID, itemEntity);
    }

    public List<UserItemEntity> getUserItemByUserId(int userID) {
        return userItemRepository.findAllByUserID(userID);
    }

    public Optional <UserItemEntity> getUserItemByUserIdAndItemId(int userID, int itemID){
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setItemID(itemID);
        return userItemRepository.findOneByUserIDAndItemID(userID, itemEntity);
    }

    @SuppressWarnings({"finally", "ReturnInsideFinallyBlock"})
    public UserItemEntity updateUserItem(int userItemID, UserItemEntity newUserItemDetails){

        UserItemEntity user_item = new UserItemEntity();

        try {
            user_item = userItemRepository.findById(userItemID).get();

            user_item.setQuantity(newUserItemDetails.getQuantity());
        } catch (NoSuchElementException ex){
            throw new NoSuchElementException ("User Item " + userItemID + " does not exist.");
        }finally {
            return  userItemRepository.save(user_item);
        }
    }

    public String deleteUserItem(int userItemID){
        String msg = "";

        if(userItemRepository.findById(userItemID).isPresent()){
            userItemRepository.deleteById(userItemID);
            msg = "Item "+ userItemID + "is successfully deleted!";
        }

        return msg;
    }

    // Buy item At Any Amount ( 1 to * )
    @Transactional
    public String butItemAnyAmount(int userID, int itemID, int itemQuantity) {
        UserDetailsEntity userDetails = null;
        ItemEntity item = null;

        try {
            userDetails = userDetailsService.getUserDetails(userID);
            item = itemService.getItem(itemID);
            int totalAmountOfItemPurchased = getTotalAmountOfItemPurchased(itemQuantity, item, userDetails);

            userDetailsService.updateUserCredit(userDetails.getUserDetailsID(), -(totalAmountOfItemPurchased));

            Optional<UserItemEntity> existingUserItemObject = getUserItemByUserIdAndItemId(userID, itemID);

            if (existingUserItemObject.isPresent()) {
                UserItemEntity existingUserItem = existingUserItemObject.get();
                int newQuantity = existingUserItem.getQuantity() + itemQuantity;
                existingUserItem.setQuantity(newQuantity);
            } else {
                UserItemEntity userItem = new UserItemEntity(itemQuantity, userID, item);
                insertUserItem(userItem);
            }

            return "Item bought successfully";

        }catch (InsufficientCreditException e) {
            return e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while processing the request.";
        }

    }

    @Transactional
    public String buyItemSingle(int userID, int itemID) {
        try {
            UserDetailsEntity userDetails = userDetailsService.getUserDetails(userID);
            ItemEntity item = itemService.getItem(itemID);
            int itemPrice = item.getPrice();

            if (userDetails.getCreditAmount() < itemPrice) {
                throw new InsufficientCreditException("Insufficient credit to buy this item.");
            }

            userDetailsService.updateUserCredit(userDetails.getUserDetailsID(), -itemPrice);

            Optional<UserItemEntity> existingUserItemObject = getUserItemByUserIdAndItemId(userID, itemID);
            if (existingUserItemObject.isPresent()) {
                UserItemEntity existingUserItem = existingUserItemObject.get();
                existingUserItem.setQuantity(existingUserItem.getQuantity() + 1);
                userItemRepository.save(existingUserItem);
            } else {
                UserItemEntity userItem = new UserItemEntity(1, userID, item);
                insertUserItem(userItem);
            }

            return "Item bought successfully";

        } catch (InsufficientCreditException e) {
            return e.getMessage();
        } catch (NoSuchElementException e) {
            return "User or Item does not exist.";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while processing the request.";
        }
    }

    private static int getTotalAmountOfItemPurchased(int itemQuantity, ItemEntity item, UserDetailsEntity userDetails) {
        int totalAmountOfItemPurchased = item.getPrice() * itemQuantity;

        if (itemQuantity <= 0) {
            throw new InvalidItemQuantityException("The item cannot be used when quantity is 0 or below.");
        }

        if (userDetails.getCreditAmount() < totalAmountOfItemPurchased) {
            throw new InsufficientCreditException("Insufficient credit to buy this item.");
        }
        return totalAmountOfItemPurchased;
    }


    public String useUserItem(int userID, int itemID){
        Optional<UserItemEntity> existingUserItemObject = getUserItemByUserIdAndItemId(userID, itemID);
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
