package com.example.Grupp9.service;

import com.example.Grupp9.dto.RegistrationUserDto;
import com.example.Grupp9.dto.UserDto;
import com.example.Grupp9.model.User;
import com.example.Grupp9.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepo userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User registerUser(RegistrationUserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
//        userDto.getRoles().forEach(role -> user.getRoles().add(role));
        user.setRoles(userDto.getRoles());

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

    public List<User> getAllUsers() {
      return userRepository.findAll();

    }
}

