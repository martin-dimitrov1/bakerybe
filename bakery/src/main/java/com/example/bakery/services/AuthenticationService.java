package com.example.bakery.services;

import com.example.bakery.exception.CustomException;
import com.example.bakery.models.AuthenticationUser;
import com.example.bakery.models.RegistrationUser;
import com.example.bakery.models.dto.UserDTO;
import com.example.bakery.models.entities.User;
import com.example.bakery.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthenticationUser login(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .map(AuthenticationUser::forExternal)
                .orElseThrow(() -> new CustomException("Wrong username or password"));
    }

    public AuthenticationUser loginWithToken(String token) {
        return findByToken(token).map(self -> {
            self.setPassword("");
            return self;
        }).orElse(null);
    }

    public UserDTO registerUser(RegistrationUser registerUser) {
        User u = new User(registerUser);
        if (u.getPassword() != null) u.setPassword(passwordEncoder.encode(registerUser.getPassword()));

        userRepository
                .findByToken(registerUser.getToken())
                .filter(dbUser -> dbUser.getRole().equals("GUEST"))
                .ifPresent(prevAnonUser -> u.setId(prevAnonUser.getId()));

        return new UserDTO(userRepository.save(u));
    }

    public boolean isUsernameAvailable(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }

    public UserDTO getUserByUsername(String username, HttpServletResponse response) {
        User userFromRepo = userRepository.findByUsername(username).orElseThrow(() -> new CustomException("User not found"));
        return new UserDTO(userFromRepo);
    }

    public Optional<AuthenticationUser> findByToken(String token) {
        return userRepository.findByToken(token).map(AuthenticationUser::forInternal);
    }
    public Optional<UserDTO> findUserByToken(String token) {
        return userRepository.findByToken(token).map(UserDTO::new);
    }

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        System.out.println("================ LOAD BY USERNAME ==============");
        return userRepository.findByToken(token)
                .map(this::toAuthUser)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid token: " + token));
    }

    public String generateHash() {
        return UUID.randomUUID().toString();
    }

    private UserDetails toAuthUser(User user) {
        return AuthenticationUser.builder()
                .authorities(Collections.singletonList((GrantedAuthority) user::getRole))
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
