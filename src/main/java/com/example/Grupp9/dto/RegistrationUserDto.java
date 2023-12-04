package com.example.Grupp9.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RegistrationUserDto {
    private String username;
    private String password;
    private String roles;
    private List<String> vehicles;

    public RegistrationUserDto() {
    }

    public RegistrationUserDto(String username, String password, String roles, List<String> vehicles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "RegistrationUserDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + roles + '\'' +
                ", vehicle='" + vehicles + '\'' +
                '}';
    }
}
