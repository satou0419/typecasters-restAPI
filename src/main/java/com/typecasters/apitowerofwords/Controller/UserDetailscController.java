package com.typecasters.apitowerofwords.Controller;

import com.typecasters.apitowerofwords.Entity.UserDetailsEntity;
import com.typecasters.apitowerofwords.Service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("user_details")
public class UserDetailscController {

    @Autowired
    UserDetailsService ud_serv;

    @PutMapping("/update_user_credit/{ud_id}/{s_credit}")
    public ResponseEntity<String> updateUserCredit(@PathVariable int ud_id, @PathVariable int s_credit){
        String result = ud_serv.updateUserCredit(ud_id, s_credit);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/increment_word")
    public ResponseEntity<String> incrementUserWordCount(@RequestParam int ud_id){
        String result = ud_serv.incrementUserDetailWords(ud_id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/increment_achievement")
    public ResponseEntity<String> incrementUserAchievementCount(@RequestParam int ud_id){
        String result = ud_serv.incrementUserAchievementCount(ud_id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/get_user_detail")
    public ResponseEntity<UserDetailsEntity> getUserDetails(@RequestParam int user_id){
        UserDetailsEntity userDetails = ud_serv.getUserDetails(user_id);
        if (userDetails != null) {
            return ResponseEntity.status(HttpStatus.OK).body(userDetails);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/get_credit_amount")
    public ResponseEntity<Integer> getCreditAmountByUserDetailsId(@RequestParam int userDetailId){
        Optional<Integer> creditAmount = ud_serv.getCreditAmountByUserDetailId(userDetailId);

        if(creditAmount.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(creditAmount.get());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
