package com.example.bakery.controllers;

import com.example.bakery.models.entities.Product;
import com.example.bakery.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    
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
}
