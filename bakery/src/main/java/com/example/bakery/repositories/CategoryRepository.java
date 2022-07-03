package com.example.bakery.repositories;

import com.example.bakery.models.dto.SubCategoryDTO;
import com.example.bakery.models.entities.Category;
import com.example.bakery.models.entities.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByMainCategoryName(String mainCategoryName);

    @Query("SELECT sc " +
            "FROM Category mc " +
            "LEFT JOIN mc.subCategories sc " +
            "WHERE mc.mainCategoryName = :mainCategory AND sc.categoryName = :subCategory")
    SubCategory findSubByMainAndSubCategoryName(String mainCategory, String subCategory);

    @Query("SELECT DISTINCT NEW com.example.bakery.models.dto.SubCategoryDTO(mc.mainCategoryName, sc.categoryName) " +
            "FROM Category mc " +
            "LEFT JOIN mc.subCategories sc " +
            "WHERE mc.mainCategoryName = :mainCategory")
    List<SubCategoryDTO> findSubsByMainCategory(String mainCategory);

    @Query("SELECT DISTINCT NEW com.example.bakery.models.dto.SubCategoryDTO(mc.mainCategoryName, sc.categoryName) " +
            "FROM Category mc " +
            "LEFT JOIN mc.subCategories sc ")
    List<SubCategoryDTO> findAllSubCategories();
}
