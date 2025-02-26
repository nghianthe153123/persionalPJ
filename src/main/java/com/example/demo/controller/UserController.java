package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("user/userProfile")
    @PreAuthorize("hasRole('USER')")
    public String helloUser(){
        return "Hello User";
    }
}
