package typecasters.tower_of_words.Service;

import typecasters.tower_of_words.Entity.UserDetailsEntity;
import typecasters.tower_of_words.Entity.UserEntity;
import typecasters.tower_of_words.Exception.IncorrectPasswordException;
import typecasters.tower_of_words.Exception.LoggedOutException;
import typecasters.tower_of_words.Exception.UsernameNotFoundException;
import typecasters.tower_of_words.LoginRequest;
import typecasters.tower_of_words.LoginResponse;
import typecasters.tower_of_words.Repository.UserDetailsRepository;
import typecasters.tower_of_words.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import typecasters.tower_of_words.UserInfo;
import typecasters.tower_of_words.UserInfoAndDetails;

import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    UserRepository urepo;

    @Autowired
    UserDetailsRepository ud_repo;

    @Autowired
    UserDetailsService ud_serv;


    @Transactional
    public String registerUser(UserEntity user) {
        try {
            if (urepo.findOneByUsername(user.getUsername()) != null) {
                throw new IllegalArgumentException("Username already exists");
            }

            if (!isValidUsername(user.getUsername())) {
                throw new IllegalArgumentException("Username must be at least 3 characters long and may optionally contain a dot (.) or underscore (_) followed by one or more lowercase letters.");
            }

            if (!isValidPassword(user.getPassword())) {
                throw new IllegalArgumentException("Password must be at least 8 characters and have at least one lowercase letter, one uppercase letter, one digit, and one special character");
            }

            user.setIsLoggedIn(false);  // Ensure isLoggedIn is set to false
            int userId = urepo.save(user).getUserID();
            ud_serv.initUserDetails(userId);

            return "Registration Successful";
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            // Catch the exception and return the error message
            return ex.getMessage();
        }
    }


    private boolean userContainsEmptyFields(UserEntity user) {
        return user.getUsername().isEmpty() || user.getPassword().isEmpty() ||
                user.getUserType().isEmpty() || user.getEmail().isEmpty() ||
                user.getFirstname().isEmpty() || user.getLastname().isEmpty();
    }

    private boolean isValidUsername(String username) {
        return username.matches("^[a-z]{3,}(?:[._][a-z]{1,})*$");
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8 && password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z\\d\\s]).+$");
    }


    // Login
//    @SuppressWarnings("finally")
//    public int login(LoginRequest logReq) {
//
//
//        UserEntity user = urepo.findOneByUsername(logReq.getUsername());
//        if (user == null) {
//            throw new UsernameNotFoundException("Username cannot be found!");
//        }
//
//        if (!user.getPassword().equals(logReq.getPassword())) {
//            throw new IncorrectPasswordException("Incorrect password.");
//        }
//        user.setIsLoggedIn(true);
//        return user.getUserID();
//    }

    @Transactional
    public LoginResponse login(LoginRequest logReq) {
        UserEntity user = urepo.findOneByUsername(logReq.getUsername());
        if (user == null) {
            throw new UsernameNotFoundException("Username cannot be found!");
        }

        if (!user.getPassword().equals(logReq.getPassword())) {
            throw new IncorrectPasswordException("Incorrect password.");
        }
        user.setIsLoggedIn(true);
        urepo.save(user);
        return new LoginResponse(user.getUserID(), user.getIsLoggedIn());
    }

    @Transactional
    public void logout(int userId) {
        UserEntity user = urepo.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
        user.setIsLoggedIn(false);

        if(user.getIsLoggedIn() == false){
            throw new LoggedOutException("User is already logged out!");
        }
        urepo.save(user);
    }

    //Account Edit
    @Transactional
    public void editAccount(UserEntity newUserInfo, int uid) {
        try {
            if (!urepo.existsById(uid)) {
                throw new NoSuchElementException("User id does not exist");
            } else {
                urepo.updateUserInfo(newUserInfo.getFirstname(), newUserInfo.getLastname(), uid);
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(e);
        }
    }

    public UserInfo findUserInfoById(int user_id){
        UserEntity user = new UserEntity();

        try{
            user = urepo.findById(user_id).get();

            if(user == null){
                throw new NoSuchElementException("User id doest not exist");
            }else{
                return new UserInfo(user.getUserID(), user.getUsername(), user.getFirstname(), user.getLastname());
            }
        }catch(NoSuchElementException e){
            throw new NoSuchElementException(e);
        }
    }

    public int findUserIdByUsername(String username) {
        UserEntity user = urepo.findOneByUsername(username);
        if (user == null) {
            throw new NoSuchElementException("User not found");
        }
        return user.getUserID();
    }

    public UserInfoAndDetails getUserInfo(int userId){
        UserEntity user = new UserEntity();
        UserDetailsEntity userDetails = new UserDetailsEntity();
        try{
            user = urepo.findById(userId).get();
            userDetails = ud_repo.findOneByUserID(userId);

            return new UserInfoAndDetails(
                    user.getUserType(),
                    user.getEmail(),
                    user.getLastname(),
                    user.getFirstname(),
                    user.getUsername(),
                    userDetails.getUserDetailsID()
                );

        }catch(NoSuchElementException e){
            throw new NoSuchElementException(e);
        }
    }

    public boolean changePassword(int userId, String oldPassword, String newPassword){
        UserEntity user = new UserEntity();

        try{
            user = urepo.findById(userId).get();

            if(user.getPassword().equals(oldPassword)){
                if (!isValidPassword(newPassword)) {
                    throw new IncorrectPasswordException("Password must be at least 8 characters and have at least one lowercase letter, one uppercase letter, one digit, and one special character");
                }

                user.setPassword(newPassword);
                urepo.save(user);
                return true;
            }else{
                return false;
            }

        }catch(NoSuchElementException e){
            throw new NoSuchElementException(e);
        }
    }

    //findTest
    public UserEntity testFind(String username){
        return urepo.findOneByUsername(username);
    }

    public boolean checkUserIfExist(String username) {
        return urepo.findByUsername(username).isPresent();
    }


}
