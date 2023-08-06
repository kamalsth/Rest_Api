package com.example.practice.dtos;

import com.example.practice.entities.Role;
import com.example.practice.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class UserDetailsDto {
    private Long userId;
    private String username;
    private String name;
    private String email;
    private String phone;
    private Role role;

    public static List<UserDetailsDto> mapToUserDetailsDto(List<User> userList) {
        List<UserDetailsDto> userDetailsDtoList = new ArrayList<>();
        userList.forEach(user -> {
            userDetailsDtoList.add(mapToUserDetailsDto(user));
        });
        return userDetailsDtoList;
    }

    public static UserDetailsDto mapToUserDetailsDto(User user) {
        return UserDetailsDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .name(user.getName())
                .phone(user.getPhone())
                .role(user.getRole())
                .build();
    }
}
