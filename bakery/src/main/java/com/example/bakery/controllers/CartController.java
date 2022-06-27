package com.example.bakery.controllers;

import com.example.bakery.models.UserProduct;
import com.example.bakery.models.dto.CartDTO;
import com.example.bakery.models.entities.Product;
import com.example.bakery.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping("/byUserId")
    public CartDTO getByUserId(@RequestParam Long userId) {
        return cartService.getUsersCartById(userId);
    }

    @GetMapping("/byUsername")
    public CartDTO getByUsername(@RequestParam String username) {
        return cartService.getUsersCartByUsername(username);
    }

    @PutMapping("/addToUserCart")
    public Product addToUserCart(@RequestBody UserProduct userProduct) {
        return cartService.addProductToUserCart(userProduct);
    }

    @PutMapping("/removeFromUserCart")
    public void removeFromUserCart(@RequestBody UserProduct userProduct) {
        cartService.removeProductFromUserCart(userProduct);
    }
}
