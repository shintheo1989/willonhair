package com.shintheo.willonhair.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shintheo.willonhair.entity.CategoryDao;
import com.shintheo.willonhair.repository.CategoryRepository;
import com.shintheo.willonhair.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public CategoryDao submitCategorie(CategoryDao categorie) {
		log.info("======>Service: submit categorie({})<======", categorie);
		return categoryRepository.save(categorie);
	}

}
