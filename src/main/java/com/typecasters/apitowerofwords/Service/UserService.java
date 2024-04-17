package com.typecasters.apitowerofwords.Service;

import com.typecasters.apitowerofwords.Entity.UserEntity;
import com.typecasters.apitowerofwords.LoginRequest;
import com.typecasters.apitowerofwords.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    UserRepository urepo;


    //Registration
    public String registerUser(UserEntity user){


        if(urepo.findOneByUsername(user.getUsername()) == null){
            urepo.save(user);
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
