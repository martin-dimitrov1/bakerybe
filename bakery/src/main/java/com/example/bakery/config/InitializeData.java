package com.example.bakery.config;

import com.example.bakery.models.entities.User;
import com.example.bakery.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class InitializeData implements ApplicationRunner {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) {
        insertSuperUser();
    }

    private void insertSuperUser() {
        if (userRepository.count() == 0) {
            User superUser = User
                    .builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    .email("")
                    .role("ADMIN")
                    .build();
            userRepository.save(superUser);
        }
    }
}
