package com.example.bakery.controllers;

import com.example.bakery.models.dto.IngredientGroupDTO;
import com.example.bakery.models.entities.customized.Ingredient;
import com.example.bakery.models.enums.CakeIngredient;
import com.example.bakery.services.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ingredient")
@CrossOrigin(origins = "http://localhost:3000")
public class IngredientController {
    private final IngredientService ingredientService;

    @GetMapping("/getAllByType")
    public List<Ingredient> getAllByType(@RequestParam CakeIngredient type) {
        return ingredientService.getAllByType(type);
    }

    @GetMapping("/getAllGroupedByType")
    public List<IngredientGroupDTO> getAllGroupedByType() {
        return ingredientService.getAllGroupedByType();
    }

    @PostMapping("/insertIngredient")
    public Ingredient insertIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.insertIngredient(ingredient);
    }

    @PutMapping("/updateIngredient")
    public Ingredient updateIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.updateIngredient(ingredient);
    }

    @DeleteMapping("/deleteIngredient")
    public void deleteIngredient(@RequestParam Long ingredientId) {
        ingredientService.deleteIngredient(ingredientId);
    }
}

