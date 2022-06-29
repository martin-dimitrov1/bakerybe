package com.example.bakery.models.entities.customized;

import com.example.bakery.models.EventDetails;
import com.example.bakery.models.PersonalInformation;
import com.example.bakery.models.dto.CustomCakeDTO;
import com.example.bakery.models.entities.AbstractEntityId;
import com.example.bakery.models.entities.Image;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "custom_cake_table")
public class CustomCake extends AbstractEntityId {
    @OneToMany(cascade = CascadeType.ALL)
    private List<Ingredient> ingredients = new ArrayList<>();
    @Embedded
    private EventDetails eventDetails = new EventDetails();
    @Embedded
    private PersonalInformation personalInformation = new PersonalInformation();
    private LocalDate neededDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @ToString.Exclude
    @JsonIgnore
    private List<Image> images = new ArrayList<>();

    public CustomCake(CustomCakeDTO customCake, List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        this.eventDetails = customCake.eventDetails();
        this.personalInformation = customCake.personalInformation();
        this.neededDate = customCake.toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(Ingredient ingredient) {
        this.ingredients.remove(ingredient);
    }

    public void addImage(Image image) {
        this.images.add(image);
    }

    public void removeImage(Long imageId) {
        this.images.removeIf(i -> Objects.equals(i.getId(), imageId));
    }

    public void removeAllImages() {
        this.images.clear();
    }
}
