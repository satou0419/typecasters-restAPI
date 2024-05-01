package com.typecasters.apitowerofwords.Service;

import com.typecasters.apitowerofwords.Entity.UserEntity;
import com.typecasters.apitowerofwords.LoginRequest;
import com.typecasters.apitowerofwords.Repository.UserDetailsRepository;
import com.typecasters.apitowerofwords.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    UserRepository urepo;

    @Autowired
    UserDetailsService ud_serv;


    //Registration
    @Transactional
    public String registerUser(UserEntity user){
        int user_id;

        if(user.getUsername().isEmpty() && user.getPassword().isEmpty() && user.getUserType().isEmpty() && user.getEmail().isEmpty() && user.getFirstname().isEmpty() && user.getLastname().isEmpty()){
            return "Fields are empty";
        }

        if(urepo.findOneByUsername(user.getUsername()) == null){

//            user_id = urepo.save(user).getUserID();
//            ud_serv.initUserDetails(user_id);

            if (user.getPassword().length() < 8) {
                return "Password must be at least 8 characters";
            }

            // Check for at least one lowercase letter
            if (!user.getPassword().matches(".*[a-z].*")) {
                return "Password must have at least one lowercase letter";
            }

            // Check for at least one uppercase letter
            if (!user.getPassword().matches(".*[A-Z].*")) {
                return "Password must have at least one uppercase letter";
            }

            // Check for at least one digit
            if (!user.getPassword().matches(".*\\d.*")) {
                return "Password must have at least one digit";
            }

            // Check for at least one special character
            Pattern specialCharPattern = Pattern.compile("[^a-zA-Z0-9]");
            Matcher matcher = specialCharPattern.matcher(user.getPassword());
            if (!matcher.find()) {
                return "Password must have at least one special character";
            }

            user_id = urepo.save(user).getUserID();
            ud_serv.initUserDetails(user_id);

            return "Registration Succesful";

        }else{
            return "Username already exist";
        }
    }

    //Login
    @SuppressWarnings("finally")
    public UserEntity login(LoginRequest logReq){
        UserEntity user = new UserEntity();
        try{
            user = urepo.findOneByUsername(logReq.getUsername());

            if(user == null){
                throw new Exception("username not found");
            }

            if(!(user.getPassword().equals(logReq.getPassword()))){
                throw new Exception("password does not match");
            }else{
                return user;
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        } finally {

        }
    }

    //Account Edit
    @SuppressWarnings("finally")
    public UserEntity editAccount(UserEntity newUserInfo, int uid){
        UserEntity user = new UserEntity();
        try {

            user = urepo.findById(uid).orElse(null);

            if(user == null){
                throw new Exception("User id does not exit");
            }else{
                user.setFirstname(newUserInfo.getFirstname());
                user.setLastname(newUserInfo.getLastname());
                user.setPassword(newUserInfo.getPassword());
                return urepo.save(user);
            }


        }catch(Exception e) {
            throw new RuntimeException(e);
        }finally {

        }
    }

    //findTest
    public UserEntity testFind(String username){
        return urepo.findOneByUsername(username);
    }
}
