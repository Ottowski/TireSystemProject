package com.example.Grupp9.controller;



import com.example.Grupp9.dto.RegistrationUserDto;
import com.example.Grupp9.model.User;


import com.example.Grupp9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;



    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }
    // endpoint: /api/login

    // endpoint: /api/admin/login


//    @PostMapping("/admin/login")
//    public ResponseEntity<String> loginAdmin(@RequestParam String username, @RequestParam String password) {
//        Optional<Admin> admin = adminService.loginAdmin(username, password);
//        if (admin.isPresent()) {
//            return ResponseEntity.ok("Admin login successful");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid admin credentials");
//        }
//    }
    // endpoint: /api/register
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationUserDto userDto) {
        try {
            System.out.println("user created");
            userService.registerUser(userDto);

            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User registration failed");
        }
    }


    @GetMapping("/allusers")
    public ResponseEntity<List<User>> getAll() {
        var allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);

    }

}


