package com.typecasters.apitowerofwords.Service;

import com.typecasters.apitowerofwords.Entity.UserEntity;
import com.typecasters.apitowerofwords.Exception.IncorrectPasswordException;
import com.typecasters.apitowerofwords.Exception.UsernameNotFoundException;
import com.typecasters.apitowerofwords.LoginRequest;
import com.typecasters.apitowerofwords.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public String registerUser(UserEntity user) {
        if (userContainsEmptyFields(user)) {
            return "Fields are empty";
        }

        if (urepo.findOneByUsername(user.getUsername()) != null) {
            return "Username already exists";
        }

        if (!isValidUsername(user.getUsername())) {
            return "Username must be at least 3 characters and have at least one lowercase letter, one uppercase letter, one digit, and one special character";
        }

        if (!isValidPassword(user.getPassword())) {
            return "Password must be at least 8 characters and have at least one lowercase letter, one uppercase letter, one digit, and one special character";
        }

        int userId = urepo.save(user).getUserID();
        ud_serv.initUserDetails(userId);

        return "Registration Successful";
    }

    private boolean userContainsEmptyFields(UserEntity user) {
        return user.getUsername().isEmpty() || user.getPassword().isEmpty() ||
                user.getUserType().isEmpty() || user.getEmail().isEmpty() ||
                user.getFirstname().isEmpty() || user.getLastname().isEmpty();
    }

    private boolean isValidUsername(String username) {
        return username.length() >= 3 && username.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z\\d\\s]).+$");
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8 && password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z\\d\\s]).+$");
    }


    // Login
    @SuppressWarnings("finally")
    public UserEntity login(LoginRequest logReq) {
        if (logReq.getUsername() == null || logReq.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Please provide a username.");
        }
        if (logReq.getPassword() == null || logReq.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Please provide a password.");
        }

        UserEntity user = urepo.findOneByUsername(logReq.getUsername());
        if (user == null) {
            throw new UsernameNotFoundException("Username cannot be found!");
        }

        if (!user.getPassword().equals(logReq.getPassword())) {
            throw new IncorrectPasswordException("Incorrect password.");
        }

        return user;
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
