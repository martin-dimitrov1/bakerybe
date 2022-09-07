package com.example.bakery.controllers;

import com.example.bakery.models.ProductOrderVO;
import com.example.bakery.models.dto.SubCategoryDTO;
import com.example.bakery.models.entities.Product;
import com.example.bakery.services.ImageService;
import com.example.bakery.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    private final ProductService productService;
    private final ImageService imageService;

    @GetMapping("/getAll")
    public List<Product> getAllProducts(@RequestParam(required = false) Optional<String> category) {
        return productService.getAllProducts(category);
    }

    @GetMapping("/getAllByCategories")
    public Page<Product> getAllByCategories(@RequestParam String category,
                                            @RequestParam(required= false) Optional<String> subCategory) {
        return productService.getAllProductsByCategories(category, subCategory);
    }

    @GetMapping("/getById")
    public Product getProductById(@RequestParam Long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/insertProduct")
    public Product insertProduct(@RequestBody Product product) {
        return productService.insertProduct(product);
    }

    @PutMapping("/updateProduct")
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/deleteProduct")
    public void deleteProduct(@RequestParam Long id) {
        productService.deleteProduct(id);
    }

    @PutMapping(value = "/addImageToProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Product addImgToProduct(@RequestParam List<MultipartFile> imgs,
                                   @RequestParam Long productId) throws IOException {
        return productService.addImgToProduct(imgs, productId);
    }

    @PutMapping("/removeImagesFromProduct")
    public Product removeImagesFromProduct(@RequestParam Long productId) {
        return productService.removeImagesFromProduct(productId);
    }

    @GetMapping("/getImagesForProduct")
    public List<String> getImagesForProduct(@RequestParam Long productId) {
        return productService.getImagesForProduct(productId);
    }

    @GetMapping("/getSubCategoriesForMain")
    public List<SubCategoryDTO> getSubCategoriesForMain(@RequestParam Optional<String> mainCategory) {
        return productService.getSubCategoriesForMain(mainCategory);
    }

    @PostMapping("/submitOrder")
    public void submitOrder(@RequestBody ProductOrderVO order) {
        productService.submitOrder(order);
    }
}
