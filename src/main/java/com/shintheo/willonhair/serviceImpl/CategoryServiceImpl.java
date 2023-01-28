package com.shintheo.willonhair.serviceImpl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import com.shintheo.willonhair.entity.CategoryDao;
import com.shintheo.willonhair.repository.CategoryRepository;
import com.shintheo.willonhair.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository catRepo;

	@Override
	public CategoryDao submitCategorie(CategoryDao categorie) {
		log.info("======>Service: submit categorie({})<======", categorie);
		return catRepo.save(categorie);
	}
	
	// Save operation
		@Override
		public CategoryDao saveCategory(CategoryDao category) {
			return catRepo.save(category);
		}
			
		// Read operation
		@Override
		public List<CategoryDao> fetchAll(){
			return catRepo.findAll();
		}
		
		@Override
		public Optional<CategoryDao> findById(Long categoryId) {
			return catRepo.findById(categoryId);
		}
			
		// Update operation
		@Override
		public CategoryDao updateCategory(CategoryDao category, Long catId) {
			// Find if category exists
			Optional<CategoryDao> opDbCat = catRepo.findById(catId);
			if(opDbCat.isPresent()) {
				category.setId(catId);
				return catRepo.save(category);				
			} else {
				throw new NotFoundException("Category not found");
			}
		}
			
		// Delete operation
		@Override
		public void deleteCategoryById(Long catId) {
			catRepo.deleteById(catId);
		}

}
