package com.example.practice.entities;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class ChangePasswordDto {
    @NotNull(message = "Old password is required")
    @NotEmpty(message = "Old password is required")
    private String oldPassword;

    @NotNull(message = "New password is required")
    @NotEmpty(message = "New password is required")
    @Size(min = 8, max = 20, message = "Password must be between 6 and 20 characters")
    @Pattern(regexp = "^(?!.*\\s).*$", message = "Password cannot contain spaces")
    @Pattern(regexp = "^(?=.*[a-z]).*$", message = "Password must contain at least one lowercase letter")
    @Pattern(regexp = "^(?=.*[A-Z]).*$", message = "Password must contain at least one uppercase letter")
    @Pattern(regexp = "^(?=.*[0-9]).*$", message = "Password must contain at least one number")
    @Pattern(regexp = "^(?=.*[!@#$%^&*]).*$", message = "Password must contain at least one special character")
    private String newPassword;
}
