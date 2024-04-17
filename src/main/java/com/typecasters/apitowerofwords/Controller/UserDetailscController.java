package com.typecasters.apitowerofwords.Controller;

import com.typecasters.apitowerofwords.Service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user_details")
public class UserDetailscController {

    @Autowired
    UserDetailsService ud_serv;

    @PutMapping("/update_user_credit/{ud_id}/{s_credit}")
    public String updateUserCredit(@PathVariable int ud_id, @PathVariable int s_credit){

        return ud_serv.updateUserCredit(ud_id,s_credit);
    }

    @PutMapping("/increment_word")
    public String incrementUserWordCount(@RequestParam int ud_id){
        return ud_serv.incrementUserDetailWords(ud_id);
    }

    @PutMapping("/increment_achievement")
    public String incrementUserAchievementCount(@RequestParam int ud_id){
        return ud_serv.incrementUserAchievementCount(ud_id);
    }
}
