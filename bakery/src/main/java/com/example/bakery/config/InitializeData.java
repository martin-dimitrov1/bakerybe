package com.example.bakery.config;

import com.example.bakery.models.entities.Product;
import com.example.bakery.models.entities.User;
import com.example.bakery.models.entities.customized.Ingredient;
import com.example.bakery.models.entities.customized.LifeCelebration;
import com.example.bakery.models.enums.CakeIngredient;
import com.example.bakery.repositories.ProductRepository;
import com.example.bakery.repositories.UserRepository;
import com.example.bakery.repositories.customized.IngredientRepository;
import com.example.bakery.repositories.customized.LifeCelebrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class InitializeData implements ApplicationRunner {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final IngredientRepository ingredientRepository;
    private final LifeCelebrationRepository lifeCelebrationRepository;

    @Override
    public void run(ApplicationArguments args) {
        insertSuperUser();
        insertProducts();
        insertIngredients();
        insertLifeCelebrations();
    }

    private void insertSuperUser() {
        if (userRepository.count() == 0) {
            User superUser = new User();
            superUser.setUsername("admin");
            superUser.setPassword(passwordEncoder.encode("admin"));
            superUser.setEmail("adminEmail@abv.bg");
            superUser.setRole("ADMIN");
            userRepository.save(superUser);
        }
    }

    private void insertProducts() {
        if (productRepository.count() == 0) {
            Product product = new Product();
            product.setName("Wedding cake");
            product.setCategory("Celebration cakes");
            product.setPrice(5);
            product.setCount(2);
            productRepository.save(product);
        }
    }

    private void insertIngredients() {
        if (ingredientRepository.count() == 0) {
            //save sponges
            ingredientRepository.saveAll(List.of(
                    new Ingredient("Genoise", 1, CakeIngredient.SPONGE),
                    new Ingredient("Chiffon Cake", 1, CakeIngredient.SPONGE),
                    new Ingredient("Angel Food Cake", 1, CakeIngredient.SPONGE),
                    new Ingredient("Victoria Sponge Cake", 1, CakeIngredient.SPONGE),
                    new Ingredient("Devil’s Food Cake", 1, CakeIngredient.SPONGE),
                    new Ingredient("Swiss Roll Sponge Cake", 1, CakeIngredient.SPONGE),
                    new Ingredient("Madeira Sponge Cake", 1, CakeIngredient.SPONGE),
                    new Ingredient("Jaconde Sponge Cake", 1, CakeIngredient.SPONGE),
                    new Ingredient("Butter Cake Sponge", 1, CakeIngredient.SPONGE)
            ));

            //save sauces
            ingredientRepository.saveAll(List.of(
                    new Ingredient("Strawberry", 1, CakeIngredient.SAUCE),
                    new Ingredient("Fresh", 1, CakeIngredient.SAUCE),
                    new Ingredient("Strawberry Tres", 1, CakeIngredient.SAUCE),
                    new Ingredient("Leches", 1, CakeIngredient.SAUCE),
                    new Ingredient("Pineapple", 1, CakeIngredient.SAUCE),
                    new Ingredient("Mango Cherry", 1, CakeIngredient.SAUCE),
                    new Ingredient("Raspberry", 1, CakeIngredient.SAUCE),
                    new Ingredient("Chocolate", 1, CakeIngredient.SAUCE),
                    new Ingredient("Bavarian Cream", 1, CakeIngredient.SAUCE),
                    new Ingredient("German Cream", 1, CakeIngredient.SAUCE),
                    new Ingredient("Cheese", 1, CakeIngredient.SAUCE),
                    new Ingredient("Pineapple", 1, CakeIngredient.SAUCE),
                    new Ingredient("Cream Cheese", 1, CakeIngredient.SAUCE),
                    new Ingredient("Lemon Creme", 1, CakeIngredient.SAUCE),
                    new Ingredient("Orange", 1, CakeIngredient.SAUCE)
            ));

            //save icings
            ingredientRepository.saveAll(List.of(
                    new Ingredient("Marshmellow", 1, CakeIngredient.ICING),
                    new Ingredient("Buttercream", 1, CakeIngredient.ICING),
                    new Ingredient("Cream", 1, CakeIngredient.ICING),
                    new Ingredient("Cheese", 1, CakeIngredient.ICING),
                    new Ingredient("Chocolate", 1, CakeIngredient.ICING),
                    new Ingredient("German", 1, CakeIngredient.ICING),
                    new Ingredient("Fondant", 1, CakeIngredient.ICING),
                    new Ingredient("Chocolate Fondant", 1, CakeIngredient.ICING)
            ));

            //save topings
            ingredientRepository.saveAll(List.of(
                    new Ingredient("Fresh fruit", 1, CakeIngredient.TOPINGS),
                    new Ingredient("Mini chocolate candies", 1, CakeIngredient.TOPINGS),
                    new Ingredient("Moldable fondant", 1, CakeIngredient.TOPINGS),
                    new Ingredient("Powdered sugar or cocoa dusted", 1, CakeIngredient.TOPINGS),
                    new Ingredient("Shredded or toasted coconut", 1, CakeIngredient.TOPINGS),
                    new Ingredient("Chopped, slivered, or toasted nuts", 1, CakeIngredient.TOPINGS),
                    new Ingredient("Small cookies cut into shapes", 1, CakeIngredient.TOPINGS),
                    new Ingredient("Chocolate curls", 1, CakeIngredient.TOPINGS),
                    new Ingredient("Candies in shapes", 1, CakeIngredient.TOPINGS)
            ));
        }
    }

    private void insertLifeCelebrations() {
        if (lifeCelebrationRepository.count() == 0) {
            lifeCelebrationRepository.saveAll(List.of(
               new LifeCelebration("Birthday"),
               new LifeCelebration("Wedding Bridal"),
               new LifeCelebration("Shower"),
               new LifeCelebration("Anniversary"),
               new LifeCelebration("Holy"),
               new LifeCelebration("Communion"),
               new LifeCelebration("Baptism"),
               new LifeCelebration("Graduation"),
               new LifeCelebration("Halloween"),
               new LifeCelebration("Christmas"),
               new LifeCelebration("Easter"),
               new LifeCelebration("Valentines"),
               new LifeCelebration("Mother's"),
               new LifeCelebration("Father's Day"),
               new LifeCelebration("4th of July"),
               new LifeCelebration("Quincenera"),
               new LifeCelebration("Get Well"),
               new LifeCelebration("Other")
            ));
        }
    }
}
