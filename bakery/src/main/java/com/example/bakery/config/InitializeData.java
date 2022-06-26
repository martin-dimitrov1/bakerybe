package com.example.bakery.config;

import com.example.bakery.models.entities.Product;
import com.example.bakery.models.entities.User;
import com.example.bakery.repositories.ProductRepository;
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
    private final ProductRepository productRepository;

    @Override
    public void run(ApplicationArguments args) {
        insertSuperUser();
        insertProducts();
    }

    private void insertSuperUser() {
        if (userRepository.count() == 0) {
            User superUser = new User();
            superUser.setUsername("admin");
            superUser.setPassword(passwordEncoder.encode("admin"));
            superUser.setEmail("adminEmail@abv.bg");
            superUser.setRole("ADMIN");
            userRepository.save(superUser);
        }
    }

    private void insertProducts() {
        if (productRepository.count() == 0) {
            Product product = new Product();
            product.setName("Wedding cake");
            product.setCategory("Celebration cakes");
            product.setPrice(5);
            product.setCount(2);
            productRepository.save(product);
        }
    }
}
