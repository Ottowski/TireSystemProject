package com.example.Grupp9.service;

import com.example.Grupp9.JwtConfig.JwtUtil;
import com.example.Grupp9.dto.AuthenticationRequest;
import com.example.Grupp9.dto.AuthenticationResponse;
import com.example.Grupp9.dto.UserDto;
import com.example.Grupp9.model.Booking;
import com.example.Grupp9.model.User;
import com.example.Grupp9.repository.UserRepo;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.example.Grupp9.dto.RegistrationUserDto;


import java.util.Collections;
import java.util.List;

import java.util.Optional;

@Service
public class UserService {


    private final PasswordEncoder passwordEncoder;
     private final UserRepo userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public UserService(PasswordEncoder passwordEncoder, UserRepo userRepository,
                       AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }


    public User registerUser(RegistrationUserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
//        userDto.getRoles().forEach(role -> user.getRoles().add(role));
        user.setRoles(Collections.singletonList(userDto.getRoles()));
        user.setVehicles(userDto.getVehicles());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> loginUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user;
        }
        return Optional.empty();
    }

    public void updatePassword(String username, String newPassword) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        } else {
            throw new RuntimeException("User not found with username: " + username);
        }
    }

    public void updateUsername(String currentUsername, String newUsername) {
        Optional<User> optionalUser = userRepository.findByUsername(currentUsername);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(newUsername);
            userRepository.save(user);
        } else {
            throw new RuntimeException("User not found with username: " + currentUsername);
        }
    }



    public List<User> getAllUsers() {
      return userRepository.findAll();

    }

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        return getAuthenticationResponse(authenticationRequest);
    }

    private AuthenticationResponse getAuthenticationResponse(AuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        User user = (User) authentication.getPrincipal();
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setRoles(user.getRoles());

        String jwt = jwtUtil.generateToken(userDto, userDto.getRoles());
        return new AuthenticationResponse(jwt, userDto);
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

//    http://localhost:8081/api/users/1
}

