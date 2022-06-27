package com.example.bakery.controllers;

import com.example.bakery.services.CustomCakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customize")
public class CustomCakeController {
    private final CustomCakeService customCakeService;
}
