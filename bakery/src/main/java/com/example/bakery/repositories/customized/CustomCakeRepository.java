package com.example.bakery.repositories.customized;

import com.example.bakery.models.entities.customized.CustomCake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomCakeRepository extends JpaRepository<CustomCake, Long> {
}
