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
    public AuthenticationUser authenticateUser(@RequestBody AuthenticationUser user) {
        return authenticationService.login(user.getEmail(), user.getPassword());
    }

    @GetMapping("/token")
    public AuthenticationUser authenticateUser(@RequestParam String token) {
        return authenticationService.loginWithToken(token);
    }

    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody RegistrationUser user) {
        return authenticationService.registerUser(user);
    }
}
