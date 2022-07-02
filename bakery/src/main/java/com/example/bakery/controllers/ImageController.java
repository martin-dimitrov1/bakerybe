package com.example.bakery.controllers;

import com.example.bakery.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/images")
@CrossOrigin(origins = "http://localhost:3000")
public class ImageController {
    private final ImageService imageService;

    @GetMapping(value = "/get", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public byte[] getImage(@RequestParam Long imgId) {
        return imageService.getImageById(imgId);
    }
}
