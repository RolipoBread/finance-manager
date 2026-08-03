package com.example.finance_manager.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank(message = "Name must not be empty")
    @Size(max = 50, message = "Name must be less than 50 characters")
    private String name;

    @NotBlank(message = "Login must not be empty")
    @Size(min = 4, max = 30, message = "Login must contain from 4 to 30 characters")
    private String login;

    @NotBlank(message = "Password must not be empty")
    @Size(min = 8, max = 100, message = "Password must contain from 8 to 100 characters")
    private String password;
}