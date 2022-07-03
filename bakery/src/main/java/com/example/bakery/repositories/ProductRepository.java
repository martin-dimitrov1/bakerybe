package com.example.bakery.repositories;

import com.example.bakery.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByMainCategory(String category);

    Optional<Product> findByName(String name);
}
