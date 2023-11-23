package com.example.Grupp9.controller;



import com.example.Grupp9.JwtConfig.JwtUtil;
import com.example.Grupp9.dto.AuthenticationRequest;
import com.example.Grupp9.dto.AuthenticationResponse;
import com.example.Grupp9.dto.RegistrationUserDto;
import com.example.Grupp9.model.User;


import com.example.Grupp9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtTokenService;



    @Autowired
    public UserController(UserService userService, JwtUtil jwtTokenService) {
        this.userService = userService;

        this.jwtTokenService = jwtTokenService;
    }
    // endpoint: /api/login

    // endpoint: /api/admin/login


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse response = userService.login(authenticationRequest);
        System.out.println(response);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, response.getJwt())
                .body(response);
    }
    // endpoint: /api/register
    @PostMapping("/register")
    public ResponseEntity<User> createUserWithRole(@RequestBody RegistrationUserDto userRegistrationDTO) {
        User savedUser = userService.registerUser(userRegistrationDTO);
        // Issue a JWT token and include it in the response headers
        var token = jwtTokenService.issueToken(userRegistrationDTO.getUsername(), userRegistrationDTO.getRoles().toString());

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, token)
                .body(savedUser);

    }


    @GetMapping("/allusers")
    public ResponseEntity<List<User>> getAll() {
        var allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);

    }

}


