package com.example.bakery.models.dto;

import com.example.bakery.models.entities.CartItem;

public record CartItemDTO(Long id,
                          String imgUrl,
                          String productName,
                          Integer quantity,
                          Integer unitPrice) {
    public CartItemDTO(CartItem item) {
        this(item.getId(), item.getImgUrl(), item.getName(), item.getQuantity(), item.getPrice());
    }
}
