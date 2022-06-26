package com.example.bakery.controllers;

import com.example.bakery.models.entities.User;
import com.example.bakery.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/getById")
    public User getById(@RequestParam Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/getByUsername")
    public User getByUsername(@RequestParam String username) {
        return userService.getUserByUsername(username);
    }

    @PutMapping("/update")
    public User authenticateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
    }
}
