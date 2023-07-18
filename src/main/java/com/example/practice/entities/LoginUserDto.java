package com.example.practice.entities;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class LoginUserDto {
    @NotNull(message = "username is required")
    @NotEmpty(message = "username  is required")
    private String username;

    @NotNull(message = "password is required")
    @NotEmpty(message = "password is required")
    private String password;


    public User toUser() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }
}
