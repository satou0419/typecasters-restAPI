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

    public Optional<Integer> getUserItemIdByUserIdAndItemId(int userId, int itemId){
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setItemID(itemId);
        return userItemRepository.findUserItemIDByUserIDAndItemID(userId, itemEntity);
    }

    public List<UserItemEntity> getUserItemByUserId(int userId) {
        return userItemRepository.findAllByUserID(userId);
    }

    public Optional <UserItemEntity> getUserItemByUserIdAndItemId(int userId, int itemId){
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setItemID(itemId);
        return userItemRepository.findOneByUserIDAndItemID(userId, itemEntity);
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

    // Buy item At Any Amount ( 1 to * )
    @Transactional
    public String butItemAnyAmount(int userId, int itemId, int itemQuantity) {
        UserDetailsEntity userDetails = null;
        ItemEntity item = null;

        try {
            userDetails = userDetailsService.getUserDetails(userId);
            item = itemService.getItem(itemId);
            int totalAmountOfItemPurchased = getTotalAmountOfItemPurchased(itemQuantity, item, userDetails);

            userDetailsService.updateUserCredit(userDetails.getUserDetailsID(), -(totalAmountOfItemPurchased));

            Optional<UserItemEntity> existingUserItemObject = getUserItemByUserIdAndItemId(userId, itemId);

            if (existingUserItemObject.isPresent()) {
                UserItemEntity existingUserItem = existingUserItemObject.get();
                int newQuantity = existingUserItem.getQuantity() + itemQuantity;
                existingUserItem.setQuantity(newQuantity);
//                userItemRepository.saveAndFlush(existingUserItem);
            } else {
                UserItemEntity userItem = new UserItemEntity(itemQuantity, userId, item);
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
    public String buyItemSingle(int userId, int itemId) {
        try {
            UserDetailsEntity userDetails = userDetailsService.getUserDetails(userId);
            ItemEntity item = itemService.getItem(itemId);
            int itemPrice = item.getPrice();

            if (userDetails.getCreditAmount() < itemPrice) {
                throw new InsufficientCreditException("Insufficient credit to buy this item.");
            }

            userDetailsService.updateUserCredit(userDetails.getUserDetailsID(), -itemPrice);

            Optional<UserItemEntity> existingUserItemObject = getUserItemByUserIdAndItemId(userId, itemId);
            if (existingUserItemObject.isPresent()) {
                UserItemEntity existingUserItem = existingUserItemObject.get();
                existingUserItem.setQuantity(existingUserItem.getQuantity() + 1);
                userItemRepository.save(existingUserItem);
            } else {
                UserItemEntity userItem = new UserItemEntity(1, userId, item);
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
