package com.example.bakery.services;

import com.example.bakery.exception.CustomException;
import com.example.bakery.models.dto.CustomCakeApproval;
import com.example.bakery.models.dto.CustomCakeDTO;
import com.example.bakery.models.entities.Image;
import com.example.bakery.models.entities.Order;
import com.example.bakery.models.entities.customized.CustomCake;
import com.example.bakery.models.entities.customized.Ingredient;
import com.example.bakery.repositories.OrderRepository;
import com.example.bakery.repositories.customized.CustomCakeRepository;
import com.example.bakery.repositories.customized.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomCakeService {
    private final CustomCakeRepository customCakeRepository;
    private final IngredientRepository ingredientRepository;
    private final OrderRepository orderRepository;

    public ResponseEntity<?> submitCustomizedCake(CustomCakeDTO customCake) {
        if (customCake.icingId() == null || customCake.sauceId() == null || customCake.spongeId() == null ||
                customCake.topingsId() == null) {
            customCake = new CustomCakeDTO(customCake);
        }
        Ingredient sponge = ingredientRepository.findById(customCake.spongeId()).orElseThrow();
        Ingredient sauce = ingredientRepository.findById(customCake.sauceId()).orElseThrow();
        Ingredient icing = ingredientRepository.findById(customCake.icingId()).orElseThrow();
        Ingredient toping = ingredientRepository.findById(customCake.topingsId()).orElseThrow();
        CustomCake customCakeEntity = new CustomCake(customCake, List.of(sponge, sauce, icing, toping));
        customCakeRepository.save(customCakeEntity);
        return ResponseEntity.ok(customCakeEntity);
    }

    public List<CustomCake> getAllCustomCakeRequests() {
        return customCakeRepository.findAll();
    }

    public ResponseEntity<?> approveCakeRequest(CustomCakeApproval approval) {
        Order order = new Order();
        return null;
    }

    public ResponseEntity<?> addImgToCustomCake(List<MultipartFile> imgs, Long customCakeId) throws IOException {
        CustomCake customCake = getCustomCakeById(customCakeId);
        customCake.removeAllImages();
        for (MultipartFile img : imgs) {
            customCake.addImage(new Image(img.getBytes()));
        }
        return ResponseEntity.ok(customCakeRepository.save(customCake));
    }

    public CustomCake getCustomCakeById(Long customCakeId) {
        return customCakeRepository.findById(customCakeId)
                .orElseThrow(() -> new CustomException("Not found custom cake with id:" + customCakeId));
    }

    public List<String> getImagesForCustomCake(Long customCakeId) {
        CustomCake customCake = getCustomCakeById(customCakeId);
        return customCake.getImages()
                .stream()
                .map(image -> "/images/get?imgId=" + image.getId())
                .collect(Collectors.toList());
    }
}
