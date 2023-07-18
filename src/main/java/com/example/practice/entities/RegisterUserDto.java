package com.example.practice.entities;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class RegisterUserDto {
    @NotNull(message = "Username is required")
    @NotEmpty(message = "Username is required")
    @Size(min = 2, max = 20, message = "Username must be between 2 and 20 characters")
    private String username;

    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password is required")
    @Size(min = 8, max = 15, message = "Password must be between 8 and 15 characters")
    @Pattern(regexp = "^(?!.*\\s).*$", message = "Password cannot contain spaces")
    @Pattern(regexp = "^(?=.*[a-z]).*$", message = "Password must contain at least one lowercase letter")
    @Pattern(regexp = "^(?=.*[A-Z]).*$", message = "Password must contain at least one uppercase letter")
    @Pattern(regexp = "^(?=.*[0-9]).*$", message = "Password must contain at least one number")
    @Pattern(regexp = "^(?=.*[!@#$%^&*]).*$", message = "Password must contain at least one special character")
    private String password;

    @NotNull(message = "Email is required")
    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    //phone number
    @NotNull(message = "Phone number is required")
    @NotEmpty(message = "Phone number is required")
    @Size(min = 10, max = 10, message = "Phone number is invalid")
    private String phone;



    public User mapToUser() {
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
//        user.setEmail(email);
//        user.setName(name);
//        user.setPhone(phone);
//        return user;

        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setPhone(phone);
        user.setEmail(email);
        user.setName(name);
        return user;
    }
}
