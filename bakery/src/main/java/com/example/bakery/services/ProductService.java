package com.example.bakery.services;

import com.example.bakery.exception.CustomException;
import com.example.bakery.models.entities.Product;
import com.example.bakery.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts(Optional<String> category) {
        if (category.isPresent()) return productRepository.findAllByCategory(category.get());
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new CustomException("Not found product with id:" + id));
    }

    public Product insertProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        Product productToBeUpdated = productRepository.findByName(product.getName())
                .orElseThrow(() -> new CustomException("Cannot update non-existant product with name:" + product.getName()));
        productToBeUpdated.update(product);
        return productRepository.save(productToBeUpdated);
    }

    public void deleteProduct(Long id) {
        Product productToBeDeleted = productRepository.findById(id)
                .orElseThrow(() -> new CustomException("Cannot delete non-existant user with id:" + id));
        productRepository.delete(productToBeDeleted);
    }
}
