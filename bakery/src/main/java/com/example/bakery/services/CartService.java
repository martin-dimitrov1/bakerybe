package com.example.bakery.services;

import com.example.bakery.exception.CustomException;
import com.example.bakery.models.CartForm;
import com.example.bakery.models.ProductOrderVO;
import com.example.bakery.models.UserProduct;
import com.example.bakery.models.dto.CartDTO;
import com.example.bakery.models.entities.*;
import com.example.bakery.repositories.CartRepository;
import com.example.bakery.repositories.OrderRepository;
import com.example.bakery.repositories.ProductRepository;
import com.example.bakery.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public CartDTO getUsersCartByToken(String token) {
        User user = userRepository.findByToken(token)
                .orElseThrow(() -> new CustomException("Not found cart for user with token: " + token));
        return getUsersCartById(user.getId());
    }

    public CartDTO getUsersCartById(Long userId) {
        return new CartDTO(cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException("Not found cart for user with id: " + userId)));
    }

    public CartDTO getUsersCartByUsername(String username) {
        return new CartDTO(cartRepository.findByUserUsername(username)
                .orElseThrow(() -> new CustomException("Not found cart for user with username: " + username)));
    }

    public CartDTO addProductToUserCart(ProductOrderVO userProduct, Long userId) {
        Long productId = userProduct.id();
        log.info("Adding product with id: " + productId + " to user with id: " + userId + " cart");
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException("Can't add product to non-existant user's cart."));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new CustomException("Can't add non-existant product to user's cart"));
        cart.addProduct(new CartItem(product, userProduct.quantity(), product.getAlergens()));
        Cart savedCart = cartRepository.save(cart);
        return new CartDTO(savedCart);
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

    public int getItemCount(String token) {
        Optional<User> byToken = userRepository.findByToken(token);
        if (byToken.isEmpty()) return 0;
        return getUsersCartByToken(token).getItemCount();
    }

    public void submitCart(CartForm form) {
        User user = userRepository.findByToken(form.token()).orElseThrow(() -> new CustomException("Invalid token"));
        Cart cart = cartRepository.findById(user.getCart().getId()).orElseThrow(() -> new CustomException("Could not submit cart for order"));
        Order order = new Order(cart, form);
        List<CartItem> cartItemsToRemove = new ArrayList<>(cart.getItems());
        cartItemsToRemove.forEach(item -> cart.removeItem(item.getId()));
        orderRepository.save(order);
    }
}
