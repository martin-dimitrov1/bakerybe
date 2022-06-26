package com.example.bakery.services;

import com.example.bakery.exception.CustomException;
import com.example.bakery.models.UserProduct;
import com.example.bakery.models.entities.Cart;
import com.example.bakery.models.entities.Product;
import com.example.bakery.models.entities.User;
import com.example.bakery.repositories.CartRepository;
import com.example.bakery.repositories.ProductRepository;
import com.example.bakery.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public Cart getUsersCartById(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException("Not found cart for user with id: " + userId));
    }

    public Cart getUsersCartByUsername(String username) {
        return cartRepository.findByUserUsername(username)
                .orElseThrow(() -> new CustomException("Not found cart for user with username: " + username));
    }

    public Product addProductToUserCart(UserProduct userProduct) {
        Long userId = userProduct.userId();
        Long productId = userProduct.productId();
        log.info("Adding product with id: " + productId + " to user with id: " + userId + " cart");
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException("Can't add product to non-existant user's cart."));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new CustomException("Can't add non-existant product to user's cart"));
        cart.addProduct(product);
        cartRepository.save(cart);
        return product;
    }

    public void removeProductFromUserCart(UserProduct userProduct) {
        Long userId = userProduct.userId();
        Long productId = userProduct.productId();
        log.info("Removing product with id: " + productId + " from user with id: " + userId + " cart");
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("Can't remove product to non-existant user's cart."));
        user.removeProductFromCart(productId);
        userRepository.save(user);
    }
}
