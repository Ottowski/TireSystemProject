package com.example.Grupp9.controller;
import ch.qos.logback.core.model.Model;
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
    // endpoint: http://localhost:8081/api/login
    /* {
       "username":  "test@test.com"
       "password":  "test"
     }*/
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse response = userService.login(authenticationRequest);
        System.out.println(response);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, response.getJwt())
                .body(response);
    }
    // endpoint: http://localhost:8081/api/register
    /*{
    "username":  "test@test.com",
    "password":  "test",
    "roles": [""],
    "vecihle": "BMW"
    }*/
    //"roles" is either ["ADMIN"] or ["USER"]

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
    // endpoint: http://localhost:8081/api/update-password
    // Token needed from user login
    // x-www-form-urlencoded:
    // Key: username Value: test@test.com
    // Key: newPassword Value: test2

    // endpoint: /api/update-password
    @PutMapping("/update-password")
    public ResponseEntity<String> updatePassword(@RequestParam String username, @RequestParam String newPassword) {
        userService.updatePassword(username, newPassword);
        return ResponseEntity.ok("Password updated successfully");
    }

    // endpoint: http://localhost:8081/api/update-username
    // Token needed from user login
    // x-www-form-urlencoded:
    // Key: currentUsername Value: test@test.com
    // Key: newUsername Value: test2@test2.com
    @PutMapping("/update-username")
    public ResponseEntity<String> updateUsername(@RequestParam String currentUsername, @RequestParam String newUsername) {
        userService.updateUsername(currentUsername, newUsername);
        return ResponseEntity.ok("Username updated successfully");
    }

    //endpoint: http://localhost:8081/api/allusers
    // Token needed from user login
    @GetMapping("/allusers")
    public ResponseEntity<List<User>> getAll() {
        var allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);

    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        var user = userService.getUserById(id);
        return ResponseEntity.ok().body(user);

    }
}