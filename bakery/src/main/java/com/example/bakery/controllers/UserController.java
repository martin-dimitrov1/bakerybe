package com.example.bakery.controllers;

import com.example.bakery.models.dto.UserDTO;
import com.example.bakery.models.entities.User;
import com.example.bakery.services.AuthenticationService;
import com.example.bakery.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @GetMapping("/getById")
    public UserDTO getById(@RequestParam Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/getByUsername")
    public UserDTO getByUsername(@RequestParam String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/getByEmail")
    public UserDTO getByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }

    @PutMapping("/update")
    public UserDTO updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/get")
    public UserDTO getUserByUsername(@RequestParam String username, HttpServletResponse response) {
        return authenticationService.getUserByUsername(username, response);
    }

    @GetMapping("/generateToken")
    public String generateHash() {
        return userService.generateHash();
    }
}
