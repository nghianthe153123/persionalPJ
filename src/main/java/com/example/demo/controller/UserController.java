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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@Slf4j
@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private MasterUserService userService;

//    @Autowired
//    private AuthenticationManager authenticationManager;

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
    public UserMaster updateUser(@RequestBody UserMaster user) {
        System.out.println(user);
//        UserMaster presentUser = (UserMaster) userService.loadUserById(user.getId());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserMaster userDetails = (UserMaster) authentication.getPrincipal();
        //case admin
        if("admin".equals(userDetails.getRole().toString())){
            return userService.save(user);
        }else if(userDetails.getId().equals(user.getId())){
            return userService.save(user);
        }
        return null;
    }

//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> authenticateUser(@RequestBody LoginDto loginDto){
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
//        System.out.println(authentication);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String username = authentication.getName();
//        LoginResponse loginResponse = new LoginResponse();
//        loginResponse.setUsername(username);
//        return ResponseEntity.ok(loginResponse);
//    }



}
