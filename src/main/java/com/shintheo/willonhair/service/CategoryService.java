package com.shintheo.willonhair.service;

import java.util.List;
import java.util.Optional;

import com.shintheo.willonhair.entity.CategoryDao;

public interface CategoryService {
	CategoryDao submitCategorie(CategoryDao categorie);
	
	// Save operation
	CategoryDao saveCategory(CategoryDao category);
	
	// Read operation
	List<CategoryDao> getServiceCategories(Long serviceId);
	
	Optional<CategoryDao> findById(Long categoryId);
	
	// Update operation
	CategoryDao updateCategory(CategoryDao category, Long cateogryId);
	
	// Delete operation
	void deleteCategoryById(Long categoryId);
}