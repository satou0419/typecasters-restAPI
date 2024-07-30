package typecasters.tower_of_words.Service;

import org.springframework.context.annotation.Lazy;
import typecasters.tower_of_words.Entity.*;
import typecasters.tower_of_words.Repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    ItemService itemService;

    @Autowired
    CharacterService characterService;

    @Autowired
    @Lazy
    UserItemService userItemService;

    @Autowired
    @Lazy
    UserCharacterService userCharacterService;

    //Initialized User Details
    public void initUserDetails(int userID){
        UserProgressEntity towerProg = new UserProgressEntity(userID, 0, 1);
        UserDetailsEntity userDetails = new UserDetailsEntity(userID,
                0,
                0,
                0,
                towerProg,
                0,
                "&melee_sword-i19-a21",
                "");

        userDetailsRepository.save(userDetails);

        UserItemEntity userItem1 = createUserItem(0, userID, 1);
        UserItemEntity userItem2 = createUserItem(0, userID, 2);
        UserItemEntity userItem3 = createUserItem(0, userID, 3);
        UserItemEntity userItem4 = createUserItem(0, userID, 4);

        userItemService.insertUserItem(userItem1);
        userItemService.insertUserItem(userItem2);
        userItemService.insertUserItem(userItem3);
        userItemService.insertUserItem(userItem4);

        UserCharacterEntity userCharacter1 = createUserCharacter(userID, true, 1);
        UserCharacterEntity userCharacter2 = createUserCharacter(userID, false, 2);
        userCharacterService.insertUserCharacter(userCharacter1);
        userCharacterService.insertUserCharacter(userCharacter2);
    }

    private UserItemEntity createUserItem(int quantity, int userID, int itemID) {
        ItemEntity itemEntity = itemService.getItem(itemID);
        return new UserItemEntity(quantity, userID, itemEntity);
    }

    private UserCharacterEntity createUserCharacter(int userID, boolean isOwned, int characterID){
        CharacterEntity characterEntity = characterService.getCharacterByID(characterID).get();
        return new UserCharacterEntity(userID, isOwned, characterEntity);
    }

    //Get user details
    public UserDetailsEntity getUserDetails(int userID){
        return userDetailsRepository.findOneByUserID(userID);
    }

    //Edit Credit
    public String updateUserCredit(int userDetailsID, int creditAmount){
        UserDetailsEntity userDetails = new UserDetailsEntity();
        String message = "";

        try{
            userDetails = userDetailsRepository.findById(userDetailsID).get();

            if(userDetails != null){
                int new_credit = userDetails.getCreditAmount() + creditAmount;
                if(new_credit < 0){
                message =  "Credit below zero.";
                }else{
                    userDetails.setCreditAmount(new_credit);
                    userDetailsRepository.save(userDetails);
                    message =  "Credit updated.";
                }
            }else{
                message = "user_detail_id does not exist";
            }
        }catch (RuntimeException e){
            message = "user_detail_id does not exist";
        }

        return message;
    }

    //Increment
    public String incrementUserDetailWords(int userID){
        UserDetailsEntity userDetails = userDetailsRepository.findOneByUserID(userID);
        userDetails.setWordsCollected(userDetails.getWordsCollected() + 1);
        userDetailsRepository.save(userDetails);

        return "word count incremented";
    }

    public String incrementUserAchievementCount(int userID){
        UserDetailsEntity userDetails = userDetailsRepository.findOneByUserID(userID);
        userDetails.setAchievementCount(userDetails.getAchievementCount() + 1);
        userDetailsRepository.save(userDetails);

        return "achievement count incremented";
    }

    public String incrementFloorCount(int userID){
        UserDetailsEntity userDetails = userDetailsRepository.findOneByUserID(userID);
        userDetails.setFloorCount(userDetails.getFloorCount()+1);
        userDetailsRepository.save(userDetails);

        return "floor count incremented";
    }

    public Optional<Integer> getCreditAmountByUserDetailId(int userDetailID) {

        return userDetailsRepository.findCreditAmountByUserDetailsID(userDetailID);
    }

    public String updateEquippedCharacter(int userDetailID, String newEquippedCharacter) {
        try {
            UserDetailsEntity userDetails = userDetailsRepository.findById(userDetailID).orElse(null);
            if (userDetails != null) {
                userDetails.setEquipped_character(newEquippedCharacter);
                userDetailsRepository.save(userDetails);
                return "Equipped character updated.";
            } else {
                return "User details not found.";
            }
        } catch (RuntimeException e) {
            return "An error occurred while updating the equipped character.";
        }
    }

    public String updateBadgeDisplay(int userDetailID, String newBadgeDisplay) {
        try {
            UserDetailsEntity userDetails = userDetailsRepository.findById(userDetailID).orElse(null);
            if (userDetails != null) {
                userDetails.setBadge_display(newBadgeDisplay);
                userDetailsRepository.save(userDetails);
                return "Badge display updated.";
            } else {
                return "User details not found.";
            }
        } catch (RuntimeException e) {
            return "An error occurred while updating the badge display.";
        }
    }
}
