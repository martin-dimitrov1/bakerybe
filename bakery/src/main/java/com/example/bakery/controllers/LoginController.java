package com.example.bakery.controllers;

import com.example.bakery.models.AuthenticationUser;
import com.example.bakery.models.RegistrationUser;
import com.example.bakery.models.dto.UserDTO;
import com.example.bakery.models.entities.User;
import com.example.bakery.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public boolean authenticateUser(@RequestBody AuthenticationUser user, HttpServletResponse response) {
        return authenticationService.authenticateUser(user, response);
    }

    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody RegistrationUser user) {
        return authenticationService.registerUser(user);
    }
}
