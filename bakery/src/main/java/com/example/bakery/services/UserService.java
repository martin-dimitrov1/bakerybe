package com.example.bakery.services;

import com.example.bakery.exception.CustomException;
import com.example.bakery.models.entities.User;
import com.example.bakery.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(new User());
    }

    public User updateUser(User user) {
        User userToBeUpdated = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new CustomException("Cannot update non-existing user."));
        if (user.getPassword() != null) user.setPassword(passwordEncoder.encode(user.getPassword()));
        userToBeUpdated.update(user);
        return userRepository.save(userToBeUpdated);
    }

    public void deleteUser(Long id) {
        User userToBeDeleted = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("Cannot delete non-existing user."));
        userRepository.delete(userToBeDeleted);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException("No such user with username:" + username));
    }
}
