package com.example.bakery.controllers;

import com.example.bakery.models.AuthenticationUser;
import com.example.bakery.models.RegistrationUser;
import com.example.bakery.models.entities.User;
import com.example.bakery.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
public class LoginController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public boolean authenticateUser(@RequestBody AuthenticationUser user) {
        return authenticationService.authenticateUser(user);
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody RegistrationUser user) {
        return authenticationService.registerUser(user);
    }
}
