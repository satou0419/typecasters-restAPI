package com.typecasters.apitowerofwords.Service;

import com.typecasters.apitowerofwords.Entity.UserDetailsEntity;
import com.typecasters.apitowerofwords.Repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {

    @Autowired
    UserDetailsRepository ud_repo;

    //Initialized User Details
    public void initUserDetails(int user_id){
        UserDetailsEntity userDetails = new UserDetailsEntity(user_id, 0, 0, 0);

        ud_repo.save(userDetails);
    }


    //Edit Credit
    public String updateUserCredit(int user_detail_id, int s_credit){
        UserDetailsEntity userDetails = new UserDetailsEntity();
        String message ;
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

        return "word count incremented";
    }
}
