package com.example.bakery.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class InitializeData implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) {

    }
}
