package com.example.Grupp9.controller;
import ch.qos.logback.core.model.Model;
import com.example.Grupp9.dto.AuthenticationRequest;
import com.example.Grupp9.dto.RegistrationUserDto;
import com.example.Grupp9.model.Booking;
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
        return "redirect:/home";
    } // Efter att man har loggat in behöver man token för att se all users ?

    @GetMapping("/home")
    public String home() {
        logger.info("Home page requested");
        return "home";
    }

    @PostMapping("/book-appointment")
    public String bookAppointment(@ModelAttribute Booking booking) {
        // logik för lagring av booking
        logger.info("Booking request: " + booking.toString());
        return "redirect:/home";
    }

}
