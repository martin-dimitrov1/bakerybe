package com.example.bakery.config;

import com.example.bakery.models.entities.Product;
import com.example.bakery.models.entities.User;
import com.example.bakery.models.entities.customized.Ingredient;
import com.example.bakery.models.entities.customized.LifeCelebration;
import com.example.bakery.models.enums.CakeIngredient;
import com.example.bakery.models.enums.MainCategoryEnum;
import com.example.bakery.repositories.ProductRepository;
import com.example.bakery.repositories.UserRepository;
import com.example.bakery.repositories.customized.IngredientRepository;
import com.example.bakery.repositories.customized.LifeCelebrationRepository;
import com.example.bakery.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Profile("bakery-dev")
@Configuration
@RequiredArgsConstructor
public class InitializeData implements ApplicationRunner {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final IngredientRepository ingredientRepository;
    private final LifeCelebrationRepository lifeCelebrationRepository;

    @Override
    public void run(ApplicationArguments args) throws IOException {
        insertSuperUser();
        insertProducts();
        insertIngredients();
        insertLifeCelebrations();
        addImagesToIngredients();
    }

    private void insertSuperUser() {
        if (userRepository.count() == 0) {
            User superUser = new User();
            superUser.setUsername("admin");
            superUser.setPassword(passwordEncoder.encode("admin"));
            superUser.setEmail("admin@abv.bg");
            superUser.setRole("ADMIN");
            superUser.setToken("admin-token");
            userRepository.save(superUser);
        }
    }

    private void insertProducts() {
        if (productRepository.count() == 0) {
            //
            Product product1 = new Product();
            product1.setName("Lady Blue");
            product1.setMainCategory(MainCategoryEnum.DESSERTS.getLabel());
            product1.setSubCategory("Pastry");
            product1.setPrice(690);
            product1.setCount(2);

            Product product2 = new Product();
            product2.setName("Pistachio");
            product2.setMainCategory(MainCategoryEnum.DESSERTS.getLabel());
            product2.setSubCategory("Pastry");
            product2.setPrice(690);
            product2.setCount(2);

            Product product3 = new Product();
            product3.setName("Brulee");
            product3.setMainCategory(MainCategoryEnum.DESSERTS.getLabel());
            product3.setSubCategory("Pastry");
            product3.setPrice(690);
            product3.setCount(5);

            Product product4 = new Product();
            product4.setName("Cheesecake");
            product4.setMainCategory(MainCategoryEnum.DESSERTS.getLabel());
            product4.setSubCategory("Pastry");
            product4.setPrice(690);
            product4.setCount(3);

            Product product5 = new Product();
            product5.setName("Trilogy");
            product5.setMainCategory(MainCategoryEnum.DESSERTS.getLabel());
            product5.setSubCategory("Pastry");
            product5.setPrice(690);
            product5.setCount(10);

            Product product6 = new Product();
            product6.setName("Garage");
            product6.setMainCategory(MainCategoryEnum.DESSERTS.getLabel());
            product6.setSubCategory("Pastry");
            product6.setPrice(690);
            product6.setCount(7);


            //STANDARD
            Product product7 = new Product();
            product7.setName("Biscuit");
            product7.setMainCategory(MainCategoryEnum.STANDARD.getLabel());
            product7.setSubCategory("");
            product7.setPrice(790);
            product7.setCount(7);

            for (int i = 0; i < 12; i++) {
                Product standardProduct = new Product();
                standardProduct.setCount(10);
                standardProduct.setMainCategory(MainCategoryEnum.STANDARD.getLabel());
                standardProduct.setSubCategory("Cakes");

                switch (i % 4) {
                    case 0 -> {
                        standardProduct.setName("Biscuit Cake");
                        standardProduct.setPrice(2590);
                    }
                    case 1 -> {
                        standardProduct.setName("Pound Cake");
                        standardProduct.setPrice(3590);
                    }
                    case 2 -> {
                        standardProduct.setName("Sponge Cake");
                        standardProduct.setPrice(2850);
                    }
                    case 3 -> {
                        standardProduct.setName("Genoise Cake");
                        standardProduct.setPrice(4850);
                    }
                }
                productService.insertProduct(standardProduct);
            }

            //CELEBRATION cakes
            for (int i = 0; i < 16; i++) {
                Product standardProduct = new Product();
                standardProduct.setCount(15);
                standardProduct.setMainCategory(MainCategoryEnum.CELEBRATION.getLabel());
                standardProduct.setSubCategory("Cakes");

                switch (i % 4) {
                    case 0 -> {
                        standardProduct.setName("Pineapple Cake");
                        standardProduct.setPrice(5344);
                    }
                    case 1 -> {
                        standardProduct.setName("Strawberry Cake");
                        standardProduct.setPrice(9312);
                    }
                    case 2 -> {
                        standardProduct.setName("Rich Chocolate Cake");
                        standardProduct.setPrice(3467);
                    }
                    case 3 -> {
                        standardProduct.setName("Fruit Cake");
                        standardProduct.setPrice(4850);
                    }
                }
                productService.insertProduct(standardProduct);
            }

            //CATERING
            for (int i = 0; i < 20; i++) {
                Product standardProduct = new Product();
                standardProduct.setCount(8);
                standardProduct.setMainCategory(MainCategoryEnum.CATERING.getLabel());

                switch (i % 4) {
                    case 0 -> {
                        standardProduct.setName("Cheesecake");
                        standardProduct.setPrice(1250);
                        standardProduct.setSubCategory("Sweet catering");
                    }
                    case 1 -> {
                        standardProduct.setName("Easter bread");
                        standardProduct.setPrice(712);
                        standardProduct.setSubCategory("Salty catering");
                    }
                    case 2 -> {
                        standardProduct.setName("Tiramisu");
                        standardProduct.setPrice(560);
                        standardProduct.setSubCategory("Sweet catering");
                    }
                    case 3 -> {
                        standardProduct.setName("Bagel");
                        standardProduct.setPrice(750);
                        standardProduct.setSubCategory("Salty catering");
                    }
                }
                productService.insertProduct(standardProduct);
            }

            List.of(product1, product2, product3, product4, product5, product6).forEach(productService::insertProduct);
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

    private void addImagesToIngredients() throws IOException {
        if (productRepository.count() != 0) {
            for (int i = 0; i <= 53; i++) {
                switch (i % 6) {
                    case 0: {
                        File file = new File("bakery/src/main/resources/templates/images/image" + (57 + i % 6) + ".jpg");
                        FileInputStream input = new FileInputStream(file);
                        MultipartFile multipartFile = new MockMultipartFile("file" + i,
                                file.getName(), "image/jpeg", input);
                        productService.addImgToProduct(List.of(multipartFile), (long) (i + 1));
                    }
                    case 1: {
                        File file = new File("bakery/src/main/resources/templates/images/image" + (57 + i % 6) + ".jpg");
                        FileInputStream input = new FileInputStream(file);
                        MultipartFile multipartFile = new MockMultipartFile("file" + i,
                                file.getName(), "image/jpeg", input);
                        productService.addImgToProduct(List.of(multipartFile), (long) (i + 1));
                    }
                    case 2: {
                        File file = new File("bakery/src/main/resources/templates/images/image" + (57 + i % 6) + ".jpg");
                        FileInputStream input = new FileInputStream(file);
                        MultipartFile multipartFile = new MockMultipartFile("file" + i,
                                file.getName(), "image/jpeg", input);
                        productService.addImgToProduct(List.of(multipartFile), (long) (i + 1));
                    }
                    case 3: {
                        File file = new File("bakery/src/main/resources/templates/images/image" + (57 + i % 6) + ".jpg");
                        FileInputStream input = new FileInputStream(file);
                        MultipartFile multipartFile = new MockMultipartFile("file" + i,
                                file.getName(), "image/jpeg", input);
                        productService.addImgToProduct(List.of(multipartFile), (long) (i + 1));
                    }
                    case 4: {
                        File file = new File("bakery/src/main/resources/templates/images/image" + (57 + i % 6) + ".jpg");
                        FileInputStream input = new FileInputStream(file);
                        MultipartFile multipartFile = new MockMultipartFile("file" + i,
                                file.getName(), "image/jpeg", input);
                        productService.addImgToProduct(List.of(multipartFile), (long) (i + 1));
                    }
                    case 5: {
                        File file = new File("bakery/src/main/resources/templates/images/image" + (57 + i % 6) + ".jpg");
                        FileInputStream input = new FileInputStream(file);
                        MultipartFile multipartFile = new MockMultipartFile("file" + i,
                                file.getName(), "image/jpeg", input);
                        productService.addImgToProduct(List.of(multipartFile), (long) (i + 1));
                    }
                }
            }
        }
    }
}
