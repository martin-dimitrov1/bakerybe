package com.example.bakery.services;

import com.example.bakery.exception.CustomException;
import com.example.bakery.models.AuthenticationUser;
import com.example.bakery.models.ProductOrderVO;
import com.example.bakery.models.RegistrationUser;
import com.example.bakery.models.dto.SubCategoryDTO;
import com.example.bakery.models.dto.UserDTO;
import com.example.bakery.models.entities.*;
import com.example.bakery.repositories.CategoryRepository;
import com.example.bakery.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CartService cartService;
    private final AuthenticationService authService;

    public List<Product> getAllProducts(Optional<String> category) {
        if (category.isPresent()) return productRepository.findAllByMainCategory(category.get());
        return productRepository.findAll();
    }

    public Page<Product> getAllProductsByCategories(String mainCategory, Optional<String> subCategory) {
        //here page should be given as param (i think) so we can increment pageNumber
        if (subCategory.isPresent()) {
            return productRepository.findAllByMainCategoryAndSubCategory(
                    mainCategory,
                    subCategory.get(),
                    PageRequest.of(1, 8, Sort.by(Sort.Order.asc("id"))));
        }
        return productRepository.findAllByMainCategory(
                mainCategory,
                PageRequest.of(1, 8, Sort.by(Sort.Order.asc("id"))));
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new CustomException("Not found product with id:" + id));
    }

    public Product insertProduct(Product product) {
        Category mainCategory = getCategoryByName(product.getMainCategory());
        if (mainCategory == null) {
            categoryRepository.save(new Category(product.getMainCategory(), product.getSubCategory()));
            return productRepository.save(product);
        }
        if (getSubCategory(product.getMainCategory(), product.getSubCategory()) == null) {
            mainCategory.addSubCategory(new SubCategory(product.getSubCategory()));
            categoryRepository.save(mainCategory);
            return productRepository.save(product);
        }
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        Product productToBeUpdated = productRepository.findByName(product.getName())
                .orElseThrow(() -> new CustomException("Cannot update non-existant product with name:" + product.getName()));
        productToBeUpdated.update(product);
        return productRepository.save(productToBeUpdated);
    }

    public void deleteProduct(Long id) {
        Product productToBeDeleted = productRepository.findById(id)
                .orElseThrow(() -> new CustomException("Cannot delete non-existant product with id:" + id));
        productRepository.delete(productToBeDeleted);
    }

    public Product addImgToProduct(List<MultipartFile> imgs, Long productId) throws IOException {
        Product product = getProductById(productId);
        product.removeAllImages();
        for (MultipartFile img : imgs) {
            product.addImage(new Image(img.getBytes()));
        }
        return productRepository.save(product);
    }

    public Product removeImagesFromProduct(Long productId) {
        Product product = getProductById(productId);
        product.removeAllImages();
        return productRepository.save(product);
    }

    public List<String> getImagesForProduct(Long productId) {
        Product product = getProductById(productId);
        return product.getImages()
                .stream()
                .map(image -> "/images/get?imgId=" + image.getId())
                .collect(Collectors.toList());
    }

    public List<SubCategoryDTO> getSubCategoriesForMain(Optional<String> mainCategory) {
        return mainCategory.map(categoryRepository::findSubsByMainCategory)
                .orElseGet(categoryRepository::findAllSubCategories);
    }

    private Category getCategoryByName(String mainCategory) {
        return categoryRepository.findByMainCategoryName(mainCategory);
    }

    private SubCategory getSubCategory(String mainCategory, String subCategory) {
        return categoryRepository.findSubByMainAndSubCategoryName(mainCategory, subCategory);
    }

    public void submitOrder(ProductOrderVO order) {
        AuthenticationUser authUser = (AuthenticationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (authUser.getRole().equals("GUEST")) {
            authService.registerUser(new RegistrationUser());
        }
        Long userId = authService.findUserByToken(authUser.getToken())
                .map(UserDTO::id).orElseThrow(() -> new CustomException("No such user"));

        cartService.addProductToUserCart(order, userId);

        //change cart entity , add in quantity and details
        // so we can work around customizecake and add it to the cart too
    }
}
