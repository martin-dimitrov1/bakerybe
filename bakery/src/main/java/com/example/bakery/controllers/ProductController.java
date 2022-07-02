package com.example.bakery.controllers;

import com.example.bakery.models.entities.Product;
import com.example.bakery.services.ImageService;
import com.example.bakery.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ImageService imageService;

    @GetMapping("/getAll")
    public List<Product> getAllProducts(@RequestParam(required = false) Optional<String> category) {
        return productService.getAllProducts(category);
    }

    @GetMapping("/getById")
    public Product getProductById(@RequestParam Long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/insertProduct")
    public Product insertProduct(@RequestBody Product product) {
        return productService.insertProduct(product);
    }

    @PutMapping("/updateProduct")
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/deleteProduct")
    public void deleteProduct(@RequestParam Long id) {
        productService.deleteProduct(id);
    }

    @PutMapping(value = "/addImageToProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Product addImgToProduct(@RequestParam List<MultipartFile> imgs,
                                   @RequestParam Long productId) throws IOException {
        return productService.addImgToProduct(imgs, productId);
    }

    @PutMapping("/removeImagesFromProduct")
    public Product removeImagesFromProduct(@RequestParam Long productId) {
        return productService.removeImagesFromProduct(productId);
    }

    @GetMapping("/getImagesForProduct")
    public List<String> getImagesForProduct(@RequestParam Long productId) {
        return productService.getImagesForProduct(productId);
    }
}
