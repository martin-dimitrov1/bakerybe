package com.example.bakery.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Bakery Service", version = "0.0.1", description = "Documentation APIs v0.0.1"))
@PropertySource(value = "classpath:config/env.properties", ignoreResourceNotFound = true)
@PropertySource("classpath:application.properties")
public class AppConfig {
    @Bean
    public PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setIgnoreUnresolvablePlaceholders(true);
        return configurer;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(5);
    }

    @Bean
    public CorsConfiguration corsConfiguration() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
        return corsConfiguration;
    }
}
