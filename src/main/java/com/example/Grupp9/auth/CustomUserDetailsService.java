package com.example.Grupp9.auth;

import com.example.Grupp9.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepository;

    public CustomUserDetailsService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }


    @Override
        public UserDetails loadUserByUsername(String username) {
            return  userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
        }
}
