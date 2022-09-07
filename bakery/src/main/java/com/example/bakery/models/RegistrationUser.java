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
    private String name;
    private String lastName;
    private String password;
    private String email;
    private String phone;
    private String receiveNotifications;
    private String token;
}
