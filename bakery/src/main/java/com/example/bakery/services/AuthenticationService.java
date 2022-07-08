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

import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public boolean authenticateUser(AuthenticationUser user, HttpServletResponse response) {
        User userFromRepo = userRepository
                .findByEmail(user.getEmail())
                .orElse(new User());
        boolean authenticated = userFromRepo.getEmail().equals(user.getEmail()) &&
                passwordEncoder.matches(user.getPassword(), userFromRepo.getPassword());
        if (authenticated) {
            response.addHeader("userId", userFromRepo.getId().toString());
            response.addHeader("userName", userFromRepo.getUsername());
            response.addHeader("userRole", userFromRepo.getRole());
        }
        return authenticated;
    }

    public UserDTO registerUser(RegistrationUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return new UserDTO(userRepository.save(new User(user)));
    }

    public boolean isUsernameAvailable(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }

    public UserDTO getUserByUsername(String username, HttpServletResponse response) {
        User userFromRepo = userRepository.findByUsername(username).orElseThrow(() -> new CustomException("User not found"));
        response.addHeader("userId", userFromRepo.getId().toString());
        response.addHeader("userName", userFromRepo.getUsername());
        response.addHeader("userRole", userFromRepo.getRole());
        return new UserDTO(userFromRepo);
    }
}
