package com.example.bakery.services;

import com.example.bakery.exception.CustomException;
import com.example.bakery.models.entities.customized.Ingredient;
import com.example.bakery.models.enums.CakeIngredient;
import com.example.bakery.repositories.customized.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public List<Ingredient> getAllByType(CakeIngredient type) {
        return ingredientRepository.findByType(type);
    }

    public Ingredient insertIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public Ingredient updateIngredient(Ingredient ingredient) {
        Ingredient ingredientFromRepo = ingredientRepository.findById(ingredient.getId())
                .orElseThrow(() -> new CustomException("Cannot update non-existing ingredient with id: " + ingredient.getId()));
        ingredientFromRepo.update(ingredient);
        return ingredientRepository.save(ingredientFromRepo);
    }

    public void deleteIngredient(Long ingredientId) {
        ingredientRepository.delete(ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new CustomException("Cannot delete non-existing ingredient with id: " + ingredientId)));
    }
}
