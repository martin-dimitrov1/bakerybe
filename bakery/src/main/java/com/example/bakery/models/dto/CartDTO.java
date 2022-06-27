package com.example.bakery.models.dto;

import com.example.bakery.models.entities.Cart;
import com.example.bakery.models.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private Long id;
    private Map<String, Integer> products = new HashMap<>();
    private String user;
    private Integer totalPrice;

    public CartDTO(Cart cart) {
        this.id = cart.getId();
        this.products = cart.getProducts().stream().collect(Collectors.toMap(Product::getName, Product::getPrice));
        this.user = cart.getUser().getUsername();
        this.totalPrice = cart.getProducts().stream().mapToInt(Product::getPrice).sum();
    }
}
