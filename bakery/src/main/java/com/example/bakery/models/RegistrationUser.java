package com.example.bakery.models;

import com.example.bakery.validation.AvailableUsername;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationUser {
    @AvailableUsername
    private String username;
    private String password;
    private String email;
}
