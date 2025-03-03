package com.example.demo.controller;

import com.example.demo.dto.LoginDto;
import com.example.demo.model.UserMaster;
import com.example.demo.service.MasterUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;



@Slf4j
@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private MasterUserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String helloUser(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        log.info("username: " + authentication.getPrincipal().toString());
//        log.info("password: " + authentication.getAuthorities());
        return "Hello User";
    }

    @PostMapping("/save")
    public UserMaster saveUser(@RequestBody UserMaster user) {
        return userService.save(user);
    }

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto, HttpServletRequest request){
        System.out.println("aaa");

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        HttpSession session = request.getSession();
        String sessionId = session.getId(); // Lấy session ID

        return ResponseEntity.ok("Session ID: " + sessionId);
    }

    //vieest api login

}
