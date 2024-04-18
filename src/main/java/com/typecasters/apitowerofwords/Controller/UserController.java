package com.typecasters.apitowerofwords.Controller;
import com.typecasters.apitowerofwords.Entity.UserEntity;
import com.typecasters.apitowerofwords.LoginRequest;
import com.typecasters.apitowerofwords.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    UserService userv;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserEntity user){
        return userv.registerUser(user);
    }

    @GetMapping("/userTest")
    public String testUser(){
        return "testinnnngg";
    }

    @PostMapping("/login")
    public UserEntity loginUser(@RequestBody LoginRequest logReq){
        return userv.login(logReq);
    }

    @PutMapping("/updateUser")
    public UserEntity updateUser(@RequestParam int uid, @RequestBody UserEntity newUserInfo){
        return userv.editAccount(newUserInfo, uid);
    }

    @PostMapping("/testFind")
    public UserEntity loginUser(@RequestBody String username){
        return userv.testFind(username);
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
    public String logoutUser(HttpServletRequest request, HttpServletResponse response){
        return "redirect:/login";
    }
}

