package com.example.practice.services;

import com.example.practice.config.CustomUserDetails;
import com.example.practice.config.JwtResponse;
import com.example.practice.config.JwtTokenUtil;
import com.example.practice.config.PasswordEncoder;
import com.example.practice.entities.ChangePasswordDto;
import com.example.practice.entities.Role;
import com.example.practice.entities.User;
import com.example.practice.exceptions.CustomMessage;
import com.example.practice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private UserRepository userRepository;
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new CustomUserDetails(user.get().getUsername(), user.get().getPassword(), user.get().getRoles());
    }

    public User getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return user.get();

    }

    public ResponseEntity<?> registerUser(User user) {
        user.setRole(Role.USER);
        user.setPassword(hashPassword(user.getPassword()));
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("username already exists");
        }
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }


    public ResponseEntity<?> loginUser(User user) {
        UserDetails userDetails = loadUserByUsername(user.getUsername());
        if (userDetails == null) {
            return ResponseEntity.badRequest().body("User not found with username: " + user.getUsername());
        }
        if (!new PasswordEncoder().matches(user.getPassword(), userDetails.getPassword())) {
            return ResponseEntity.badRequest().body(new CustomMessage("Password is not correct"));
        }
        String token = jwtTokenUtil.generateToken(userDetails);
        JwtResponse jwtResponse = new JwtResponse(token);
        return ResponseEntity.ok(jwtResponse);
    }

    public String hashPassword(String password) {
        return new PasswordEncoder().encodePassword(password);
    }

    public void changePassword(String username, String newPassword) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        user.get().setPassword(hashPassword(newPassword));
        userRepository.save(user.get());
    }

    public ResponseEntity<?> changePassword(ChangePasswordDto requestPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserDetails userDetails = loadUserByUsername(username);
        if (!new PasswordEncoder().matches(requestPassword.getOldPassword(), userDetails.getPassword())) {
            return ResponseEntity.badRequest().body(new CustomMessage("Old password is not correct"));

        }else if(requestPassword.getOldPassword().equals(requestPassword.getNewPassword())){
            return ResponseEntity.badRequest().body(new CustomMessage("Old password and new password should not be same"));
        }
        changePassword(username, requestPassword.getNewPassword());
        return ResponseEntity.ok(new CustomMessage("Password changed successfully"));
    }
    
}
