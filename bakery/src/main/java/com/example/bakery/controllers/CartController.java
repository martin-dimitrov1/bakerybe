package com.example.bakery.controllers;

import com.example.bakery.models.CartForm;
import com.example.bakery.models.UserProduct;
import com.example.bakery.models.dto.CartDTO;
import com.example.bakery.models.entities.Product;
import com.example.bakery.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {
    private final CartService cartService;

    @GetMapping("/byUserId")
    public CartDTO getByUserId(@RequestParam Long userId) {
        return cartService.getUsersCartById(userId);
    }

    @GetMapping("/byToken")
    public CartDTO getByToken(@RequestParam String token) {
        return cartService.getUsersCartByToken(token);
    }

    @GetMapping("/byUsername")
    public CartDTO getByUsername(@RequestParam String username) {
        return cartService.getUsersCartByUsername(username);
    }

    @PutMapping("/removeFromUserCart")
    public void removeFromUserCart(@RequestBody UserProduct userProduct) {
        cartService.removeProductFromUserCart(userProduct);
    }

    @GetMapping("/items/count")
    public int getCartItemsCount(@RequestParam String token) {
       return cartService.getItemCount(token);
    }

    @PostMapping("/submitCart")
    public void submitCart(@RequestBody CartForm form) {
       cartService.submitCart(form);
    }
}
