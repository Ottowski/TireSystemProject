package com.example.Grupp9.controller;
import ch.qos.logback.core.model.Model;
import com.example.Grupp9.dto.AuthenticationRequest;
import com.example.Grupp9.dto.RegistrationUserDto;
import com.example.Grupp9.model.User;
import com.example.Grupp9.service.UserService;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {

    private static final Logger logger = LoggerFactory.getLogger(WebController.class);

    private final UserService userService;

    @Autowired
    public WebController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration() {
        logger.info("Registration page requested");
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/register-web")
    public String registerUserWeb(@ModelAttribute RegistrationUserDto registrationUserDto) {
        User savedUser = userService.registerUser(registrationUserDto);
        return "redirect:/login";
    }

    @PostMapping("login-web")
    public String loginUserWeb(@ModelAttribute AuthenticationRequest authenticationRequest, Model model) {
        return "redirect:/api/allusers";
    }
}
