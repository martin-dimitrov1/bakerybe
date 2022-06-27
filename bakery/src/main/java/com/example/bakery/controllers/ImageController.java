package com.example.bakery.controllers;

import com.example.bakery.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/images")
public class ImageController {
    private final ImageService imageService;

    @GetMapping(value = "/get", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public byte[] getImage(@RequestParam Long imgId) {
        return imageService.getImageById(imgId);
    }
}
