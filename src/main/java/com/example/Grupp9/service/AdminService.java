//package com.example.Grupp9.service;
//
//import com.example.Grupp9.model.Admin;
//import com.example.Grupp9.repository.UserRepo;
//import org.apache.catalina.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class AdminService {
//
//    private final UserRepo userRepository;
//
//    @Autowired
//    public AdminService(UserRepo userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public Optional<User> loginUser(String username, String password) {
//        return userRepository.findByUsernameAndPassword(username, password);
//    }
//
//    public Optional<Admin> loginAdmin(String username, String password) {
//        if ("admin".equals(username) && "password".equals(password)) {
//            Admin admin = new Admin();
//            admin.setUsername(username);
//            admin.setPassword(password);
//            return Optional.of(userRepository.save(admin));
//        } else {
//            return Optional.empty();
//        }
//    }
//}
