package com.example.bakery.controllers;

import com.example.bakery.models.dto.CustomCakeApproval;
import com.example.bakery.models.dto.CustomCakeDTO;
import com.example.bakery.models.entities.Product;
import com.example.bakery.models.entities.customized.CustomCake;
import com.example.bakery.services.CustomCakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customize")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomCakeController {
    private final CustomCakeService customCakeService;

    @GetMapping("/getAllRequests")
    public List<CustomCake> getAllRequests() {
        return customCakeService.getAllCustomCakeRequests();
    }

    @PostMapping("/submitForm")
    public ResponseEntity<?> submitCustomizedCake(@RequestBody CustomCakeDTO customCake) {
        return customCakeService.submitCustomizedCake(customCake);
    }

    @PutMapping(value = "/addImageToCustomCake", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addImgToCustomCake(@RequestParam List<MultipartFile> imgs,
                                   @RequestParam Long customCakeId) throws IOException {
        return customCakeService.addImgToCustomCake(imgs, customCakeId);
    }

    @PostMapping("/approveCakeRequest")
    public ResponseEntity<?> approveCakeRequest(@RequestBody CustomCakeApproval approval) {
        return customCakeService.approveCakeRequest(approval);
    }

    @GetMapping("/getImagesForCustomCake")
    public List<String> getImagesForCustomCake(@RequestParam Long customCakeId) {
        return customCakeService.getImagesForCustomCake(customCakeId);
    }
}
