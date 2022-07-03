package com.example.bakery.models.entities;

import com.example.bakery.models.entities.AbstractEntityId;
import com.example.bakery.models.entities.Product;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subcategory_table")
public class SubCategory extends AbstractEntityId {
    private String categoryName;

    @OneToMany(cascade = CascadeType.DETACH)
    private List<Product> productList = new ArrayList<>();

    public SubCategory(String sub) {
        this.categoryName = sub;
        this.productList = new ArrayList<>();
    }
}
