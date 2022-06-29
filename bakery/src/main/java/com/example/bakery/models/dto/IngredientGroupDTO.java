package com.example.bakery.models.dto;

import com.example.bakery.models.entities.customized.Ingredient;
import com.example.bakery.models.enums.CakeIngredient;

import java.util.List;

public record IngredientGroupDTO(CakeIngredient type,
                                 List<Ingredient> ingredients) {
}
