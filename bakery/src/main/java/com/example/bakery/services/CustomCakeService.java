package com.example.bakery.services;

import com.example.bakery.repositories.customized.CustomCakeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomCakeService {
    private final CustomCakeRepository customCakeRepository;
}
