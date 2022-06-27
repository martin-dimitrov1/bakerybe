package com.example.bakery.services;

import com.example.bakery.exception.CustomException;
import com.example.bakery.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ImageService {
    private final ImageRepository imageRepository;

    public byte[] getImageById(Long imageId) {
        return imageRepository.findById(imageId)
                .orElseThrow(() -> new CustomException("Cannot find image with id=" + imageId)).getImg();
    }
}
