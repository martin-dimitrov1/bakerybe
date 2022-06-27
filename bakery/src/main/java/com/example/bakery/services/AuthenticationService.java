package com.example.bakery.services;

import com.example.bakery.exception.CustomException;
import com.example.bakery.models.AuthenticationUser;
import com.example.bakery.models.RegistrationUser;
import com.example.bakery.models.dto.UserDTO;
import com.example.bakery.models.entities.User;
import com.example.bakery.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public boolean authenticateUser(AuthenticationUser user) {
        User userFromRepo = userRepository
                .findByUsername(user.getUsername())
                .orElse(new User());
        return userFromRepo.getUsername().equals(user.getUsername()) &&
                passwordEncoder.matches(user.getPassword(), userFromRepo.getPassword());
    }

    public UserDTO registerUser(RegistrationUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return new UserDTO(userRepository.save(new User(user)));
    }

    public boolean isUsernameAvailable(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }
}
