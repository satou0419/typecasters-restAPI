package com.typecasters.apitowerofwords.Controller;
import com.typecasters.apitowerofwords.Entity.UserEntity;
import com.typecasters.apitowerofwords.Exception.IncorrectPasswordException;
import com.typecasters.apitowerofwords.Exception.UsernameNotFoundException;
import com.typecasters.apitowerofwords.LoginRequest;
import com.typecasters.apitowerofwords.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userv;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserEntity user) {
        try {
            String registrationStatus = userv.registerUser(user);
            // Check if registration was successful
            if ("Registration Successful".equals(registrationStatus)) {
                return ResponseEntity.ok(registrationStatus);
            } else {
                // Registration failed, return error message
                return ResponseEntity.badRequest().body(registrationStatus);
            }
        } catch (IllegalArgumentException ex) {
            // Catch specific exception thrown by service layer and return error message
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception e) {
            // Handle unexpected exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during registration");
        }
    }



    @GetMapping("/user_test")
    public String testUser(){
        return "testinnnngg";
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody LoginRequest logReq) {
        try {
            UserEntity user = userv.login(logReq);
            return ResponseEntity.ok(user); // 200 OK
        }

        catch (IllegalArgumentException e) {
            // Bad request for missing or invalid input
            return ResponseEntity.badRequest().body(e.getMessage()); // 400 Bad Request
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




    @PutMapping("/update_user")
    public UserEntity updateUser(@RequestParam int uid, @RequestBody UserEntity newUserInfo){
        return userv.editAccount(newUserInfo, uid);
    }

    @PostMapping("/test_find")
    public UserEntity loginUser(@RequestBody String username){
        return userv.testFind(username);
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
    public String logoutUser(HttpServletRequest request, HttpServletResponse response){
        return "redirect:/login";
    }
}

