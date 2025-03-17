package com.example.demo.controller;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.resonse.LoginResponse;
import com.example.demo.dto.resonse.UserProfileResponse;
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
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Slf4j
@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private MasterUserService userService;
    private final ExecutorService executorService = new DelegatingSecurityContextExecutorService(Executors.newFixedThreadPool(5));

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/myProfile")
//    @PreAuthorize("hasRole('USER')")
    public UserProfileResponse profile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        UserProfileResponse userProfileResponse = new UserProfileResponse();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserMaster userDetails = (UserMaster) authentication.getPrincipal();
            System.out.println(userDetails.getAge());
            userProfileResponse.setUsername(userDetails.getId()+"");
            userProfileResponse.setRole(userDetails.getRole().toString());

        }
        return userProfileResponse;
    }

    @PostMapping("/save")
    public UserMaster saveUser(@RequestBody UserMaster user) {
        return userService.save(user);
    }


    @PutMapping("/update")
    public CompletableFuture<ResponseEntity<?>> updateUser(@RequestBody UserMaster user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return CompletableFuture.supplyAsync(() -> {
            try {
                return ResponseEntity.ok(userService.save(user));
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Error updating user: " + e.getMessage());
            }
        }, executorService);
    }
//        System.out.println(user);
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserMaster userDetails = (UserMaster) authentication.getPrincipal();
//        //case admin
//        if("admin".equals(userDetails.getRole().toString()) || userDetails.getId().equals(user.getId())){
//            return ResponseEntity.ok(userService.save(user));
//        }
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("can not access");
    }

//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> authenticateUser(@RequestBody LoginDto loginDto){
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String username = authentication.getName();
//        LoginResponse loginResponse = new LoginResponse();
//        loginResponse.setUsername(username);
//        return ResponseEntity.ok(loginResponse);
//    }




