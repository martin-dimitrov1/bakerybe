package com.example.bakery.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@RequiredArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Entity
@Table(name = "cart_item_table")
public class CartItem extends AbstractEntityId {
    private Long productId;
    private String imgUrl;
    private Integer quantity;
    private String details;
    private String name;
    private Integer price;

    public CartItem(Product product, Integer quantity, String details) {
        this.productId = product.getId();
        this.quantity = quantity;
        this.details = details;
        this.name = product.getName();
        this.price = product.getPrice();
        this.imgUrl =  "/images/get?imgId=" + product.getImages().get(0).getId();
    }

    @Override
    public String toString() {
        return "name: " + name + ", price: " + price;
    }
}
