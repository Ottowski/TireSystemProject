package com.example.Grupp9.repository;

import com.example.Grupp9.model.Admin;
import com.example.Grupp9.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<org.apache.catalina.User> findByUsernameAndPassword(String username, String password);

    Optional<Admin> findAdminByUsernameAndPassword(String username, String password);
}