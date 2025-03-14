package com.example.demo.controller;

import com.example.demo.model.UserMaster;
import com.example.demo.service.MasterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller("/")

class AuthController {
    @Autowired
    private MasterUserService userService;

    @GetMapping("/home")
    public String showHomePage(Model model) {
        return "login";
    }

//    @GetMapping("/login")
//    String login() {
//        return "login";
//    }
    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String saveUser(
            @RequestParam String username,
            @RequestParam String password,
            RedirectAttributes redirectAttributes) { // Thêm RedirectAttributes

        UserMaster user = new UserMaster();
        user.setUsername(username);
        user.setPassword(password);
        userService.save(user);

        // Thêm thông báo vào flash attributes
        redirectAttributes.addFlashAttribute("message", "Login Success!");

        // Chuyển hướng đến trang login
        return "login";
    }


}
