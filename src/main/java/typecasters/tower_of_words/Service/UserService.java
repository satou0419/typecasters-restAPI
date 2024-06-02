package typecasters.tower_of_words.Service;

import typecasters.tower_of_words.Entity.UserEntity;
import typecasters.tower_of_words.Exception.IncorrectPasswordException;
import typecasters.tower_of_words.Exception.UsernameNotFoundException;
import typecasters.tower_of_words.LoginRequest;
import typecasters.tower_of_words.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import typecasters.tower_of_words.UserInfo;

import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    UserRepository urepo;

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

            int userId = urepo.save(user).getUserID();
            ud_serv.initUserDetails(userId);

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
    public UserEntity login(LoginRequest logReq) {


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

    public UserInfo findUserInfoById(int user_id){
        UserEntity user = new UserEntity();


        try{
            user = urepo.findById(user_id).get();

            return new UserInfo(user.getUserID(), user.getUsername(), user.getFirstname(), user.getLastname());

        }catch(NoSuchElementException e){
            throw new NoSuchElementException(e);
        }
    }

    public int findUserIdByUsername(String username){
        UserEntity user = urepo.findOneByUsername(username);

        return user.getUserID();
    }

    //findTest
    public UserEntity testFind(String username){
        return urepo.findOneByUsername(username);
    }
}
