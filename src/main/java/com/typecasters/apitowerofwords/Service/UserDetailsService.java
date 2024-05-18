package com.typecasters.apitowerofwords.Service;

import com.typecasters.apitowerofwords.Entity.TowerProgressEntity;
import com.typecasters.apitowerofwords.Entity.UserDetailsEntity;
import com.typecasters.apitowerofwords.Repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService {

    @Autowired
    UserDetailsRepository ud_repo;

    //Initialized User Details
    public void initUserDetails(int user_id){
        TowerProgressEntity towerProg = new TowerProgressEntity(user_id, 1, 1);
        UserDetailsEntity userDetails = new UserDetailsEntity(user_id, 0, 0, 0, towerProg);

        ud_repo.save(userDetails);
    }

    //Get user details
    public UserDetailsEntity getUserDetails(int user_id){
        return ud_repo.findOneByUserId(user_id);
    }

    //Edit Credit
    public String updateUserCredit(int user_detail_id, int s_credit){
        UserDetailsEntity userDetails = new UserDetailsEntity();
        String message = "";

        try{
            userDetails = ud_repo.findById(user_detail_id).get();

            if(userDetails != null){
                int new_credit = userDetails.getCredit_amount() + s_credit;
                if(new_credit < 0){
                message =  "Credit below zero.";
                }else{
                    userDetails.setCredit_amount(new_credit);
                    ud_repo.save(userDetails);
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
    public String incrementUserDetailWords(int user_detail_id){
        UserDetailsEntity userDetails = ud_repo.findById(user_detail_id).get();
        userDetails.setWords_collected(userDetails.getWords_collected() + 1);
        ud_repo.save(userDetails);

        return "word count incremented";
    }

    public String incrementUserAchievementCount(int user_detail_id){
        UserDetailsEntity userDetails = ud_repo.findById(user_detail_id).get();
        userDetails.setAchievement_count(userDetails.getAchievement_count() + 1);
        ud_repo.save(userDetails);

        return "achievement count incremented";
    }

    public Optional<Integer> getCreditAmountByUserDetailId(int userDetailId) {

        return ud_repo.findCreditAmountByUserDetailId(userDetailId);
    }
}
