package com.example.bakery.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category_table")
public class Category extends AbstractEntityId {
    private String mainCategoryName;

    @OneToMany(cascade = CascadeType.ALL)
    private List<SubCategory> subCategories = new ArrayList<>();
}
