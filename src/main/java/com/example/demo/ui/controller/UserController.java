package com.example.demo.ui.controller;

import com.example.demo.ui.model.request.UserDetailsRequestModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users") // https://localhost:8080/users
public class UserController {

    @GetMapping
    public String getUser(){
        return "get user was called";
    }

    @PostMapping
    public String createUser(){
        return  "create user has called";
    }

    @PutMapping
    public String updateUser(@RequestBody UserDetailsRequestModel userDetails){
        return  "update user was called";
    }

    @DeleteMapping
    public String deleteUser(){
        return "delete user was called";
    }

}
