package com.example.bakery.repositories.customized;

import com.example.bakery.models.entities.customized.Ingredient;
import com.example.bakery.models.enums.CakeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findByType(CakeIngredient type);
}
