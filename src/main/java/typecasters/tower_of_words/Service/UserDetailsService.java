package typecasters.tower_of_words.Service;

import org.springframework.context.annotation.Lazy;
import typecasters.tower_of_words.Entity.ItemEntity;
import typecasters.tower_of_words.Entity.UserItemEntity;
import typecasters.tower_of_words.Entity.UserProgressEntity;
import typecasters.tower_of_words.Entity.UserDetailsEntity;
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
    @Lazy
    UserItemService userItemService;

    //Initialized User Details
    public void initUserDetails(int user_id){
        UserProgressEntity towerProg = new UserProgressEntity(user_id, 0, 1);
        UserDetailsEntity userDetails = new UserDetailsEntity(user_id, 0, 0, 0, towerProg);

        userDetailsRepository.save(userDetails);

        UserItemEntity userItem1 = createUserItem(0, user_id, 1);
        UserItemEntity userItem2 = createUserItem(0, user_id, 2);
        UserItemEntity userItem3 = createUserItem(0, user_id, 3);
        UserItemEntity userItem4 = createUserItem(0, user_id, 4);

        userItemService.insertUserItem(userItem1);
        userItemService.insertUserItem(userItem2);
        userItemService.insertUserItem(userItem3);
        userItemService.insertUserItem(userItem4);
    }
    private UserItemEntity createUserItem(int quantity, int userId, int itemId) {
        ItemEntity itemEntity = itemService.getItem(itemId);
        return new UserItemEntity(quantity, userId, itemEntity);
    }



    //Get user details
    public UserDetailsEntity getUserDetails(int user_id){
        return userDetailsRepository.findOneByUserID(user_id);
    }

    //Edit Credit
    public String updateUserCredit(int user_detail_id, int s_credit){
        UserDetailsEntity userDetails = new UserDetailsEntity();
        String message = "";

        try{
            userDetails = userDetailsRepository.findById(user_detail_id).get();

            if(userDetails != null){
                int new_credit = userDetails.getCreditAmount() + s_credit;
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
    public String incrementUserDetailWords(int user_id){
        UserDetailsEntity userDetails = userDetailsRepository.findOneByUserID(user_id);
        userDetails.setWordsCollected(userDetails.getWordsCollected() + 1);
        userDetailsRepository.save(userDetails);

        return "word count incremented";
    }

    public String incrementUserAchievementCount(int user_id){
        UserDetailsEntity userDetails = userDetailsRepository.findOneByUserID(user_id);
        userDetails.setAchievementCount(userDetails.getAchievementCount() + 1);
        userDetailsRepository.save(userDetails);

        return "achievement count incremented";
    }

    public Optional<Integer> getCreditAmountByUserDetailId(int userDetailId) {

        return userDetailsRepository.findCreditAmountByUserDetailsID(userDetailId);
    }
}
