package com.example.Grupp9.service;

<<<<<<< HEAD
import com.example.Grupp9.model.User;
import com.example.Grupp9.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

=======
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
>>>>>>> config-new
import java.util.Optional;

@Service
public class UserService {
<<<<<<< HEAD
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepository;

    public User registerUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
=======
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
>>>>>>> config-new
        return userRepository.save(user);
    }

    public Optional<User> loginUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user;
        }
        return Optional.empty();
    }
<<<<<<< HEAD
=======

    public List<User> getAllUsers() {
      return userRepository.findAll();

    }
>>>>>>> config-new
}

