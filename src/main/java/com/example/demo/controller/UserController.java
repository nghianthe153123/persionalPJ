package com.example.demo.controller;

import com.example.demo.model.UserMaster;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.MasterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private MasterUserService userService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("user/userProfile")
    @PreAuthorize("hasRole('USER')")
    public String helloUser(){
        return "Hello User";
    }

    @PostMapping("/save")
    public UserMaster saveUser(@RequestBody UserMaster user) {
        return userService.save(user);
    }
}
