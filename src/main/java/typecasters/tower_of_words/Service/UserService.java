package typecasters.tower_of_words.Service;

import typecasters.tower_of_words.Entity.UserDetailsEntity;
import typecasters.tower_of_words.Entity.UserEntity;
import typecasters.tower_of_words.Exception.IncorrectPasswordException;
import typecasters.tower_of_words.Exception.UsernameNotFoundException;
import typecasters.tower_of_words.LoginRequest;
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
    UserRepository userRepository;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    UserDetailsService userDetailsService;


    @Transactional
    public String registerUser(UserEntity user) {
        try {


            if (userRepository.findOneByUsername(user.getUsername()) != null) {
                throw new IllegalArgumentException("Username already exists");
            }

            if (!isValidUsername(user.getUsername())) {
                throw new IllegalArgumentException("Username must be at least 3 characters long and may optionally contain a dot (.) or underscore (_) followed by one or more lowercase letters.");
            }

            if (!isValidPassword(user.getPassword())) {
                throw new IllegalArgumentException("Password must be at least 8 characters and have at least one lowercase letter, one uppercase letter, one digit, and one special character");
            }

            int userId = userRepository.save(user).getUserID();
            userDetailsService.initUserDetails(userId);

            return "Registration Successful";
        } catch (IllegalArgumentException ex) {
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
    @SuppressWarnings("finally")
    public int login(LoginRequest logReq) {


        UserEntity user = userRepository.findOneByUsername(logReq.getUsername());
        if (user == null) {
            throw new UsernameNotFoundException("Username cannot be found!");
        }

        if (!user.getPassword().equals(logReq.getPassword())) {
            throw new IncorrectPasswordException("Incorrect password.");
        }
        user.setIsLoggedIn(true);
        return user.getUserID();
    }

    //Account Edit
    @SuppressWarnings("finally")
    public UserEntity editAccount(UserEntity newUserInfo, int uid){
        UserEntity user = new UserEntity();
        try {

            user = userRepository.findById(uid).orElse(null);

            if(user == null){
                throw new Exception("User id does not exit");
            }else{
                user.setFirstname(newUserInfo.getFirstname());
                user.setLastname(newUserInfo.getLastname());
                user.setPassword(newUserInfo.getPassword());
                return userRepository.save(user);
            }


        }catch(Exception e) {
            throw new RuntimeException(e);
        }finally {

        }
    }

    public UserInfo findUserInfoById(int user_id){
        UserEntity user = new UserEntity();


        try{
            user = userRepository.findById(user_id).get();

            return new UserInfo(user.getUserID(), user.getUsername(), user.getFirstname(), user.getLastname());

        }catch(NoSuchElementException e){
            throw new NoSuchElementException(e);
        }
    }

    public int findUserIdByUsername(String username){
        UserEntity user = userRepository.findOneByUsername(username);

        return user.getUserID();
    }

    public UserInfoAndDetails getUserInfo(int userId){
        UserEntity user = new UserEntity();
        UserDetailsEntity userDetails = new UserDetailsEntity();
        try{
            user = userRepository.findById(userId).get();
            userDetails = userDetailsRepository.findOneByUserID(userId);

            return new UserInfoAndDetails(user.getUserType(), user.getEmail(), user.getLastname(), user.getFirstname(), user.getUsername(), userDetails.getUserDetailsID());
        }catch(NoSuchElementException e){
            throw new NoSuchElementException(e);
        }
    }

    public boolean changePassword(int userId, String oldPassword, String newPassword){
        UserEntity user = new UserEntity();

        try{
            user = userRepository.findById(userId).get();

            if(user.getPassword().equals(oldPassword)){
                user.setPassword(newPassword);

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
        return userRepository.findOneByUsername(username);
    }


}
