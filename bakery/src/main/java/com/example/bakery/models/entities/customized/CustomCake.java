package com.example.bakery.models.entities.customized;

import com.example.bakery.models.EventDetails;
import com.example.bakery.models.PersonalInformation;
import com.example.bakery.models.dto.CustomCakeDTO;
import com.example.bakery.models.entities.AbstractEntityId;
import com.example.bakery.models.entities.Image;
import com.example.bakery.models.enums.CakeIngredient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "custom_cake_table")
public class CustomCake extends AbstractEntityId {
    @ElementCollection
    @MapKeyEnumerated(EnumType.STRING)
    private Map<CakeIngredient, String> ingredients = new HashMap<>();
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
        this.ingredients = ingredients.stream().collect(Collectors.toMap(Ingredient::getType, Ingredient::getName ));
        this.eventDetails = new EventDetails(customCake.celebration(), customCake.theme(), customCake.tiers(), customCake.guests());
        this.personalInformation = new PersonalInformation(customCake.firstName(), customCake.lastName(), customCake.email(), customCake.phone(), false);
        if (customCake.toDate() == null) this.neededDate = LocalDate.now().plusDays(10);
        else this.neededDate = customCake.toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
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
