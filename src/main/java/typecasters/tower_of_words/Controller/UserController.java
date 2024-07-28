package typecasters.tower_of_words.Controller;
import typecasters.tower_of_words.Entity.UserEntity;
import typecasters.tower_of_words.Exception.IncorrectPasswordException;
import typecasters.tower_of_words.Exception.LoggedOutException;
import typecasters.tower_of_words.Exception.UsernameNotFoundException;
import typecasters.tower_of_words.LoginRequest;
import typecasters.tower_of_words.LoginResponse;
import typecasters.tower_of_words.Response.NoDataResponse;
import typecasters.tower_of_words.Response.Response;
import typecasters.tower_of_words.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import typecasters.tower_of_words.UserInfo;
import typecasters.tower_of_words.UserInfoAndDetails;

import java.util.NoSuchElementException;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody UserEntity user) {
        try {
            String registrationStatus = userService.registerUser(user);

            if ("Registration Successful".equals(registrationStatus)) {
                return NoDataResponse.noDataResponse(HttpStatus.OK, "Registration Successful");
            } else {
                return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, registrationStatus);
            }
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error has occurred during registration");
        }
    }

    @GetMapping("/user_test")
    public String testUser(){
        return "testinnnngg";
    }

    @GetMapping("/get_user_id/{username}")
    public ResponseEntity<Object> getUserId(@PathVariable String username){
        try{
            int userId = userService.findUserIdByUsername(username);
            return Response.response(HttpStatus.OK, "User ID found", userId);
        }catch(NoSuchElementException e){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
        }catch(Exception e){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody LoginRequest logReq) {
        try {
            LoginResponse loginResponse = userService.login(logReq);
            return Response.response(HttpStatus.OK, "Login successful", loginResponse); // 200 OK
        } catch (UsernameNotFoundException e) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage()); // 404 Not Found
        } catch (IncorrectPasswordException e) {
            return NoDataResponse.noDataResponse(HttpStatus.UNAUTHORIZED, e.getMessage()); // 401 Unauthorized
        } catch (RuntimeException e) {
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An internal error occurred. Please try again later."); // 500 Internal Server Error
        }
    }

    @GetMapping("/get_user_info/{userID}")
    public ResponseEntity<Object> getUserInfo(@PathVariable int userID) {
        try {
            UserInfoAndDetails userInfoAndDetails = userService.getUserInfo(userID);
            return Response.response(HttpStatus.OK, "User info found", userInfoAndDetails);
        } catch (NoSuchElementException e) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "User not found");
        } catch (Exception e){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PatchMapping("/change_password")
    public ResponseEntity<Object> changePassword(@RequestParam int userID, @RequestParam String oldPassword, @RequestParam String newPassword) {
        try {
            boolean passwordChanged = userService.changePassword(userID, oldPassword, newPassword);
            if (passwordChanged) {
                return NoDataResponse.noDataResponse(HttpStatus.OK, "Password changed successfully");
            } else {
                return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, "Old password is incorrect");
            }
        } catch (NoSuchElementException e) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "User not found");
        } catch (IncorrectPasswordException e){
            return NoDataResponse.noDataResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
        }catch (Exception e){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PatchMapping("/update_user")
    public ResponseEntity<Object> updateUser(@RequestParam int userID, @RequestBody UserEntity newUserInfo) {
        try {
            userService.editAccount(newUserInfo, userID);
            return NoDataResponse.noDataResponse(HttpStatus.OK, "User updated successfully");
        } catch (NoSuchElementException e) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "User not found");
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update user");
        }
    }

    @PostMapping("/test_find")
    public UserEntity loginUser(@RequestBody String username){
        return userService.testFind(username);
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.PATCH)
    public ResponseEntity<Object> logoutUser(@RequestParam int userID) {
        try {
            userService.logout(userID);
            return NoDataResponse.noDataResponse(HttpStatus.OK, "User logged out successfully");
        } catch (LoggedOutException e){
            return NoDataResponse.noDataResponse(HttpStatus.CONFLICT, e.getMessage());
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to logout user");
        }
    }

    @GetMapping("/checkExist/{username}")
    public ResponseEntity<Object> checkUserExistence(@PathVariable String username) {
        try {
            boolean userExists = userService.checkUserIfExist(username);

            if(userExists){
                return Response.response(HttpStatus.OK, "User exists", userExists);
            }else{
                return Response.response(HttpStatus.NOT_FOUND, "User doesn't exist", userExists);
            }
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to check user existence");
        }
    }
}

