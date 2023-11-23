package com.example.Grupp9.dto;


import java.util.Collection;

public class UserDto {
    private String username;
    private Collection<String> roles;

    public UserDto() {
    }

    public UserDto(String username, Collection<String> roles) {
        this.username = username;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", role='" + roles + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public Collection<String> getRoles() {
        return roles;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRoles(Collection<String> roles) {
        this.roles = roles;
    }

}
