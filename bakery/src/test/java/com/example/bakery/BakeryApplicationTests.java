package com.example.bakery;

import com.example.bakery.controllers.*;
import com.example.bakery.models.AuthenticationUser;
import com.example.bakery.models.CartForm;
import com.example.bakery.models.RegistrationUser;
import com.example.bakery.models.dto.CustomCakeDTO;
import com.example.bakery.models.entities.Product;
import com.example.bakery.models.enums.PhotoSimilarityOptions;
import com.example.bakery.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("bakery-test")
@SpringBootTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@TestPropertySource(locations = "classpath:application-test.properties")
class BakeryApplicationTests {
    private static final String BASE_URL = "http://localhost:9010";
    @Autowired
    LoginController authController;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartController cartController;
    @Autowired
    ProductController productController;
    @Autowired
    UserController userController;
    @Autowired
    CustomCakeController customCakeController;
    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        closeable = MockitoAnnotations.openMocks(this);
    }


    @Test
    void registerUser_WithCorrectData() {
        // prepare existing user
        RegistrationUser user = RegistrationUser.builder()
                .name("username")
                .password("a582sd08")
                .build();
        authController.registerUser(user);

        assertEquals(1, userRepository.findAll().size());
    }

    @Test
    void registerUser_AndLogin() {
        // prepare existing user
        RegistrationUser user = RegistrationUser.builder()
                .name("username")
                .password("a582sd08")
                .build();
        authController.registerUser(user);

        assertEquals(1, userRepository.findAll().size());
        authController.authenticateUser(AuthenticationUser.builder()
                .username("username")
                .password("a582sd08")
                .build());
    }

    @Test
    void registerUser_WithoutUniqueName() {
        // prepare existing user
        RegistrationUser user = RegistrationUser.builder()
                .name("username")
                .password("a582sd08")
                .build();
        authController.registerUser(user);

        RegistrationUser existing = RegistrationUser.builder()
                .name("username")
                .password("q322erKi4")
                .build();
        // The controller threw an exception
        assertThrows(
                Exception.class,
                () -> authController.registerUser(existing));
        // There is only 1 user registered
        assertEquals(1, userRepository.findAll().size());
    }

    @Test
    void addProduct_WithCorrectData() {
        Product product = new Product();
        product.setCount(5);
        product.setAlergens("allergens");
        product.setMainCategory("mainCategory");
        product.setSubCategory("subCategory");
        product.setName("productName");
        productController.insertProduct(product);
    }

    @Test
    void addProduct_WithIncorrectPrice() {
        Product product = new Product();
        product.setCount(5);
        product.setAlergens("allergens");
        product.setMainCategory("mainCategory");
        product.setSubCategory("subCategory");
        product.setPrice(-5);
        product.setName("productName");
        productController.insertProduct(product);
    }

    @Test
    void addProduct_WithNewSubCategory() {
        Product product1 = new Product();
        product1.setCount(5);
        product1.setAlergens("allergens");
        product1.setMainCategory("mainCategory");
        product1.setSubCategory("subCategory");
        product1.setPrice(5);
        product1.setName("product1Name");
        productController.insertProduct(product1);

        Product product = new Product();
        product.setCount(5);
        product.setAlergens("allergens");
        product.setMainCategory("mainCategory");
        product.setSubCategory("subCategory1");
        product.setPrice(5);
        product.setName("productName");
        productController.insertProduct(product);
    }

    @Test
    void addProductToCart() {
        Product product1 = new Product();
        product1.setCount(5);
        product1.setPrice(5);
        product1.setName("product1Name");
        productController.insertProduct(product1);
//        productController.submitOrder(new ProductOrderVO(1L, 5));
    }

    @Test
    void submitCustomizedCake() {
        assertThrows(
                Exception.class,
                () -> cartController.submitCart(
                        new CartForm("address", new Date(), "time", "token")));
    }

    @Test
    void submitOrder_WithWrongData() {
        customCakeController.submitCustomizedCake(
                new CustomCakeDTO(1L, 10L, 25L, 33L, "celebration", "theme",
                        1, 4, PhotoSimilarityOptions.YES, "firstName",
                        "lastName", "email", "phone", new Date()));
    }

    @AfterEach
    void close() throws Exception {
        closeable.close();
    }
}
