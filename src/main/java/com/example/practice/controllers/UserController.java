package com.example.practice.controllers;

import com.example.practice.dtos.ChangePasswordDto;
import com.example.practice.dtos.LoginUserDto;
import com.example.practice.dtos.RegisterUserDto;
import com.example.practice.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {
    private UserService userService;


    @PostMapping("/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody @Validated RegisterUserDto registerRequest) {
        return userService.registerUser(registerRequest.mapToUser());
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> loginUser(@RequestBody @Validated LoginUserDto loginRequest) {
       return userService.loginUser(loginRequest.toUser());
    }

    @PostMapping("/changePassword")
    public ResponseEntity<?>changePassword(@RequestBody @Validated ChangePasswordDto passwordRequest){
        return userService.changePassword(passwordRequest);
    }


    @GetMapping("/getCurrentUser")
    public ResponseEntity<?>getCurrentUser(){
        return userService.getCurrentUser();
    }

}
