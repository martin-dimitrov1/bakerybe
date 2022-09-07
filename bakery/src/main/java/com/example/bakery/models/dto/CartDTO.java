package com.example.bakery.models.dto;

import com.example.bakery.models.entities.Cart;
import com.example.bakery.models.entities.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private Long id;
    private List<CartItemDTO> items;
    private String user;
    private Integer totalPrice;
    private Integer itemCount;
    private String token;

    public CartDTO(Cart cart) {
        this.id = cart.getId();
        this.items = cart.getItems().stream().map(CartItemDTO::new).collect(Collectors.toList());
        this.user = cart.getUser().getUsername();
        this.itemCount = this.items.size();
        this.totalPrice = cart.getItems().stream().mapToInt(CartItem::getPrice).sum();
        this.token = cart.getUser().getToken();
    }
}
