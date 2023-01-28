package com.shintheo.willonhair.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shintheo.willonhair.api.CategoryApi;
import com.shintheo.willonhair.base.Status;
import com.shintheo.willonhair.entity.CategoryDao;
import com.shintheo.willonhair.exception.storage.StorageFileNotFoundException;
import com.shintheo.willonhair.service.CategoryService;
import com.shintheo.willonhair.service.storage.StorageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
public class CategoryController implements CategoryApi {

	@Autowired
	private final CategoryService service;
	
	@Autowired
	private final StorageService storageService;

	@Override
	public ResponseEntity<CategoryDao> submitCategorie(@Valid @RequestBody CategoryDao category) {
		log.info("======>RestController: submit categorie<======");
		category.setId(null);
		category.setStatus(Status.VISIBLE);
		return ResponseEntity.ok(service.submitCategorie(category));
	}

	@Override
	public ResponseEntity<List<CategoryDao>> all() {
		return ResponseEntity.ok(service.fetchAll());
	}

	@Override
	public ResponseEntity<CategoryDao> one(Long categoryId) throws NotFoundException {
		return ResponseEntity.ok(service.findById(categoryId).orElseThrow());
	}

	@Override
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<String> deleteCategory(Long catId) {
		service.deleteCategoryById(catId);
		return ResponseEntity.ok("Category Deleted");
	}

	@Override
	public ResponseEntity<CategoryDao> updateCategory(
			Long catId, 
			@RequestBody CategoryDao category, 
			MultipartFile file) throws NotFoundException {
		if(file != null) {
			String newImageName = storageService.storeImage(file);
			category.setImage(newImageName);
		}
		return ResponseEntity.ok(service.updateCategory(category, catId));
	}

}