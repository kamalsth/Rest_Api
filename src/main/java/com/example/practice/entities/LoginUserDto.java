package com.example.practice.entities;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class LoginUserDto {
    @NotNull(message = "Username is required")
    @NotEmpty(message = "Username is required")
    private String username;

    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password is required")
    private String password;


    public User toUser() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }
}
