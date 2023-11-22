package com.example.Grupp9.controller;


import com.example.Grupp9.model.Admin;
import com.example.Grupp9.model.User;
import com.example.Grupp9.service.AdminService;
import com.example.Grupp9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final AdminService adminService;

    @Autowired
    public UserController(UserService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }
    // endpoint: /api/login
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
        Optional<User> user = userService.loginUser(username, password);
        if (user.isPresent()) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
    // endpoint: /api/admin/login
    @PostMapping("/admin/login")
    public ResponseEntity<String> loginAdmin(@RequestParam String username, @RequestParam String password) {
        Optional<Admin> admin = adminService.loginAdmin(username, password);
        if (admin.isPresent()) {
            return ResponseEntity.ok("Admin login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid admin credentials");
        }
    }
    // endpoint: /api/register
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestParam String username, @RequestParam String password) {
        try {
            userService.registerUser(username, password);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User registration failed");
        }
    }
}

