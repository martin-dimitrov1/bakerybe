package com.example.bakery.models.dto;

import com.example.bakery.models.entities.User;

public record UserDTO(
        Long id,
        String username,
        String email,
        String role) {
    public UserDTO(User user) {
        this(user.getId(), user.getUsername(), user.getEmail(), user.getRole());
    }
}
