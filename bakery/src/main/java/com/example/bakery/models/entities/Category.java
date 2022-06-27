package com.example.bakery.models.entities;

import com.example.bakery.models.SubCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
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

    @ElementCollection
    private List<SubCategory> subCategories = new ArrayList<>();
}
