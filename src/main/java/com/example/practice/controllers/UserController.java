package com.example.practice.controllers;

import com.example.practice.config.JwtResponse;
import com.example.practice.config.JwtTokenUtil;
import com.example.practice.config.PasswordEncoder;
import com.example.practice.entities.LoginUserDto;
import com.example.practice.entities.RegisterUserDto;
import com.example.practice.services.CustomUserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {
    private CustomUserDetailService customUserDetailService;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Validated RegisterUserDto registerRequest) {
        return customUserDetailService.registerUser(registerRequest.mapToUser());
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @Validated LoginUserDto loginRequest) {
       return customUserDetailService.loginUser(loginRequest.toUser());
    }


}
