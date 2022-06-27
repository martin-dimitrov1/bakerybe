package com.example.bakery.models;

import com.example.bakery.models.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class SubCategory {
    private String categoryName;

    @OneToMany(cascade = CascadeType.DETACH)
    private List<Product> productList = new ArrayList<>();
}
