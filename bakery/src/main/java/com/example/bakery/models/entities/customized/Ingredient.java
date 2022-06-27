package com.example.bakery.models.entities.customized;


import com.example.bakery.models.entities.AbstractEntityId;
import com.example.bakery.models.enums.CakeIngredient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ingredient_table")
public class Ingredient extends AbstractEntityId {
    private String name;
    private Integer price;
    @Enumerated(EnumType.STRING)
    private CakeIngredient type;

    public void update(Ingredient ingredient) {
        if (ingredient.getName() != null) this.setName(ingredient.getName());
        if (ingredient.getPrice() != null) this.setPrice(ingredient.getPrice());
    }
}
