package com.example.bakery.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_table")
public class Product extends AbstractEntityId {
    private String name;
    private String category;
    private Integer price;
    private Integer count;
    private String alergens;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @ToString.Exclude
    @JsonIgnore
    private List<Image> images = new ArrayList<>();

    public void update(Product product) {
        if (product.getName() != null) this.setName(product.getName());
        if (product.getCategory() != null) this.setCategory(product.getCategory());
        if (product.getPrice() != null) this.setPrice(product.getPrice());
        if (product.getCount() != null) this.setCount(product.getCount());
        if (product.getAlergens() != null) this.setAlergens(product.getAlergens());
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
