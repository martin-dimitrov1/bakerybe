package com.example.bakery.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

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

    public void update(Product product) {
        if(product.getName()!= null) this.setName(product.getName());
        if(product.getCategory() != null) this.setCategory(product.getCategory());
        if(product.getPrice() != null) this.setPrice(product.getPrice());
        if(product.getCount() != null) this.setCount(product.getCount());
    }
}
