package com.example.demo.controller;

import com.example.demo.model.UserMaster;
import com.example.demo.service.MasterUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;



@Slf4j
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("username: " + authentication.getPrincipal().toString());
        log.info("password: " + authentication.getAuthorities());
        return "Hello User";
    }

    @PostMapping("/save")
    public UserMaster saveUser(@RequestBody UserMaster user) {
        return userService.save(user);
    }
}
