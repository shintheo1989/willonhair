package com.shintheo.willonhair.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shintheo.willonhair.entity.ServiceDao;
import com.shintheo.willonhair.entity.CategoryDao;
import com.shintheo.willonhair.repository.ServiceRepository;
import com.shintheo.willonhair.repository.CategoryRepository;
import com.shintheo.willonhair.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository catRepo;
	@Autowired
	private ServiceRepository serviceRepo;

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
		public List<CategoryDao> getServiceCategories(Long serviceId){
			ServiceDao service = serviceRepo.findById(serviceId).get();
			return new ArrayList<CategoryDao>(service.getCategories());
		}
		
		@Override
		public Optional<CategoryDao> findById(Long categoryId) {
			return catRepo.findById(categoryId);
		}
			
		// Update operation
		@Override
		public CategoryDao updateCategory(CategoryDao category, Long catId) {
			CategoryDao dbCat = catRepo.findById(catId).get();
			dbCat.setName(category.getName());
			dbCat.setImageName(category.getImageName());
			dbCat.setNote(category.getNote());
			return catRepo.save(dbCat);
		}
			
		// Delete operation
		@Override
		public void deleteCategoryById(Long catId) {
			catRepo.deleteById(catId);
		}

}
