package com.example.practice.services;

import com.example.practice.config.CustomUserDetails;
import com.example.practice.entities.Role;
import com.example.practice.entities.User;
import com.example.practice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User>user=userRepository.findByUsername(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("User not found with username: "+ username);
        }
        return new CustomUserDetails(user.get().getUsername(),user.get().getPassword(),user.get().getRoles());
    }

    public User getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: "+ username);
        }
        return user.get();

    }

//    public ResponseEntity<?> registerUser(User user) {
//        user.setRole(Role.USER);
//        user.setPassword(hashPassword(user.getPassword()));
//        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
//        if (existingUser.isPresent()) {
//            return ResponseEntity.badRequest().body(new CustomMessage("Username already exists").toString());
//        }
//        userRepository.save(user);
//        return ResponseEntity.ok(new CustomMessage("User registered successfully").toString());
//    }
//
//    public String hashPassword(String password) {
//        return new
//    }


}
