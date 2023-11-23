package com.example.Grupp9.dto;


import lombok.Data;

@Data
public class AuthenticationResponse {
    private final String jwt;
    private final UserDto user;

    public AuthenticationResponse(String jwt, UserDto user) {
        this.jwt = jwt;
        this.user = user;
    }



}
