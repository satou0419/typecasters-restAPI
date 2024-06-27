package typecasters.tower_of_words.Controller;
import typecasters.tower_of_words.Entity.UserEntity;
import typecasters.tower_of_words.Exception.IncorrectPasswordException;
import typecasters.tower_of_words.Exception.UsernameNotFoundException;
import typecasters.tower_of_words.LoginRequest;
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
    UserService userv;

//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestBody UserEntity user) {
//        try {
//            String registrationStatus = userv.registerUser(user);
//            // Check if registration was successful
//            if ("Registration Successful".equals(registrationStatus)) {
//                return ResponseEntity.ok(registrationStatus);
//            } else {
//                // Registration failed, return error message
//                return ResponseEntity.badRequest().body(registrationStatus);
//            }
//        } catch (IllegalArgumentException ex) {
//            // Catch specific exception thrown by service layer and return error message
//            return ResponseEntity.badRequest().body(ex.getMessage());
//        } catch (Exception e) {
//            // Handle unexpected exceptions
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during registration");
//        }
//    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody UserEntity user) {
        try {
            String registrationStatus = userv.registerUser(user);

            if ("Registration Successful".equals(registrationStatus)) {
                return NoDataResponse.noDataResponse(HttpStatus.OK, "Registration Successful");
            } else {
                return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, registrationStatus);
            }
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error has occurred during registration");
        }
    }




    @GetMapping("/user_test")
    public String testUser(){
        return "testinnnngg";
    }

//    @GetMapping("/get_user_id/{username}")
//    public int getUserId(@PathVariable String username){
//        return userv.findUserIdByUsername(username);
//    }
//
    @GetMapping("/get_user_id/{username}")
    public ResponseEntity<Object> getUserId(@PathVariable String username){
        try{
            int userId = userv.findUserIdByUsername(username);
            return Response.response(HttpStatus.OK, "User ID found", userId);
        }catch(NoSuchElementException e){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
        }catch(Exception e){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

//    @GetMapping("/get_user_info/{user_id}")
//    public UserInfo getUserInfo(@PathVariable int user_id){
//        return userv.findUserInfoById(user_id);
//    }

//    @GetMapping("/get_user_info/{user_id}")
//    public ResponseEntity<Object> getUserInfo(@PathVariable int user_id){
//        try{
//            UserInfo userInfo = userv.findUserInfoById(user_id);
//            return Response.response(HttpStatus.OK, "User Info found", userInfo);
//        }catch(NoSuchElementException e){
//            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
//        }catch(Exception e){
//            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
//        }
//    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody LoginRequest logReq) {
        try {
            int userId = userv.login(logReq);
            return ResponseEntity.ok(userId); // 200 OK
        }

        catch (UsernameNotFoundException e) {
            // Username not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // 404 Not Found
        }

        catch (IncorrectPasswordException e) {
            // Incorrect password
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage()); // 401 Unauthorized
        }

        catch (RuntimeException e) {
            // Internal Server Error for any other unhandled error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An internal error occurred. Please try again later."); // 500 Internal Server Error
        }
    }

    @GetMapping("/get_user_info/{user_id}")
    public ResponseEntity<Object> getUserInfo(@PathVariable int user_id) {
        try {
            UserInfoAndDetails userInfoAndDetails = userv.getUserInfo(user_id);
            return Response.response(HttpStatus.OK, "User info found", userInfoAndDetails);
        } catch (NoSuchElementException e) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "User not found");
        } catch (Exception e){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/change_password")
    public ResponseEntity<Object> changePassword(@RequestParam int user_id, @RequestParam String oldPassword, @RequestParam String newPassword) {
        try {
            boolean passwordChanged = userv.changePassword(user_id, oldPassword, newPassword);
            if (passwordChanged) {
                return NoDataResponse.noDataResponse(HttpStatus.OK, "Password changed successfully");
            } else {
                return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, "Old password is incorrect");
            }
        } catch (NoSuchElementException e) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "User not found");
        } catch (Exception e){
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    @PutMapping("/update_user")
    public ResponseEntity<Object> updateUser(@RequestParam int uid, @RequestBody UserEntity newUserInfo) {
        try {
            userv.editAccount(newUserInfo, uid);
            return NoDataResponse.noDataResponse(HttpStatus.OK, "User updated successfully");
        } catch (NoSuchElementException e) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "User not found");
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update user");
        }
    }


    @PostMapping("/test_find")
    public UserEntity loginUser(@RequestBody String username){
        return userv.testFind(username);
    }

//    @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
//    public String logoutUser(HttpServletRequest request, HttpServletResponse response, int user_id){
//
//        return "redirect:/login";
//    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
    public ResponseEntity<Object> logoutUser(@RequestParam int userId) {
        try {
            userv.logout(userId);
//            response.sendRedirect("/login");
            return NoDataResponse.noDataResponse(HttpStatus.OK, "User logged out successfully");
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to logout user");
        }
    }

    @GetMapping("/checkExist/{username}")
    public ResponseEntity<Object> checkUserExistence(@PathVariable String username) {
        try {
            boolean userExists = userv.checkUserIfExist(username);

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

