package com.example.bakery.services;

import com.example.bakery.exception.CustomException;
import com.example.bakery.models.dto.IngredientGroupDTO;
import com.example.bakery.models.entities.customized.Ingredient;
import com.example.bakery.models.enums.CakeIngredient;
import com.example.bakery.repositories.customized.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<IngredientGroupDTO> getAllGroupedByType() {
        List<Ingredient> ingredientsSponge = ingredientRepository.findByType(CakeIngredient.SPONGE);
        List<Ingredient> ingredientsSauce = ingredientRepository.findByType(CakeIngredient.SAUCE);
        List<Ingredient> ingredientsIcing = ingredientRepository.findByType(CakeIngredient.ICING);
        List<Ingredient> ingredientsTopings = ingredientRepository.findByType(CakeIngredient.TOPINGS);
        List<IngredientGroupDTO> ingredientGroup = new ArrayList<>();
        ingredientGroup.add(new IngredientGroupDTO(CakeIngredient.SPONGE, ingredientsSponge));
        ingredientGroup.add(new IngredientGroupDTO(CakeIngredient.SAUCE, ingredientsSauce));
        ingredientGroup.add(new IngredientGroupDTO(CakeIngredient.ICING, ingredientsIcing));
        ingredientGroup.add(new IngredientGroupDTO(CakeIngredient.TOPINGS, ingredientsTopings));
        return ingredientGroup;
    }
}
