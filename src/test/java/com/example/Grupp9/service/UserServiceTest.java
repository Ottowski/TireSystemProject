package com.example.Grupp9.service;

import com.example.Grupp9.JwtConfig.JwtUtil;
import com.example.Grupp9.dto.AuthenticationRequest;
import com.example.Grupp9.dto.AuthenticationResponse;
import com.example.Grupp9.dto.RegistrationUserDto;
import com.example.Grupp9.dto.UserDto;
import com.example.Grupp9.exception.NotFoundException;
import com.example.Grupp9.model.User;
import com.example.Grupp9.repository.UserRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@DataJpaTest
class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepo userRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtUtil;
    @BeforeEach
    void setUp() {
        userService = new UserService(passwordEncoder, userRepository, authenticationManager, jwtUtil);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void registerUser() {
        RegistrationUserDto registrationUserDto = new RegistrationUserDto();
        registrationUserDto.setUsername("test");
        registrationUserDto.setPassword("test");

        // Mocking behavior for userRepository
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        User resultUser = userService.registerUser(registrationUserDto);

        // THEN
        verify(userRepository).save(any(User.class));
        assertEquals(registrationUserDto.getUsername(), resultUser.getUsername());
        assertEquals(passwordEncoder.encode(registrationUserDto.getPassword()), resultUser.getPassword());

    }

    @Test
    void updatePassword() {
        // Arrange
        String username = "someUsername";
        String newPassword = "newPassword";
        User existingUser = User.builder()
                .username(username)
                .password("oldPassword")
                .build();

        // Mocking behavior for userRepository
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        userService.updatePassword(username, newPassword);

        // Assert
        assertEquals(passwordEncoder.encode(newPassword), existingUser.getPassword());

        System.out.println(" -->> before all ??" + existingUser + "oldPassword: " + existingUser.getPassword() );
        System.out.println(" -->> after all ??" + existingUser  + "newPassword: " + newPassword );
    }

    @Test
    void updateUsername() {
        // Given
        String username = "someUsername";
        String newUsername = "newUsername";
        User existingUser = User.builder()
                .username(username)
                .password("oldPassword")
                .build();

        // Mocking behavior for userRepository
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        userService.updateUsername(username, newUsername);

        // Then
        assertEquals(newUsername, existingUser.getUsername());

    }

    @Test
    void getAllUsers() {
        // Given
        when(userRepository.findAll()).thenReturn(List.of(
                new User(1, "test", "test", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()),
                new User(2, "test2", "test2", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()),
                new User(3, "test3", "test3", new ArrayList<>(), new ArrayList<>(), new ArrayList<>())
        ));

        // When
        List<User> users = userService.getAllUsers();

        // Then
        assertEquals(3, users.size());
        assertEquals(1, users.get(0).getId());
        assertEquals(2, users.get(1).getId());
        assertEquals(3, users.get(2).getId());

        verify(userRepository).findAll();

        users.forEach(System.out::println);

    }

    @Test
    void login() {
        // Given
        String username = "test";
        String password = "test";
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(username, password);

        UserDetails userDetails = User.builder()
                .username(username)
                .password(password)
                .build();

        // Mocking behavior for authenticationManager
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(new UsernamePasswordAuthenticationToken(userDetails, null));

        // Mocking behavior for jwtUtil
        when(jwtUtil.generateToken(any(UserDto.class), any()))
                .thenReturn("token");

        // when
        AuthenticationResponse authenticationResponse = userService.login(authenticationRequest);

        // Then
        verify(authenticationManager).authenticate(any());
        verify(jwtUtil).generateToken(any(UserDto.class), any());

        // Assertions
        assertEquals("token", authenticationResponse.getJwt());
        assertEquals(username, authenticationResponse.getUser().getUsername());
        // Add more assertions as needed
    }





    @Test
    void getUserById() {
        // Given
        Long userId = 1L;
        User mockUser = User.builder()
                .id(Math.toIntExact(userId))
                .username("test")
                .password("test")
                .build();
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        // When
        User resultUser = userService.getUserById(userId);

        // Then
        assertEquals(mockUser, resultUser);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testGetUserByIdWhenUserDoesNotExist() {
        // Given
        Long userId = 2L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // When and Then
        assertThrows(NotFoundException.class, () -> userService.getUserById(userId));
        verify(userRepository, times(1)).findById(userId);
    }
}