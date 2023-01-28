package com.shintheo.willonhair.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shintheo.willonhair.entity.CategoryDao;
import com.shintheo.willonhair.exception.storage.StorageFileNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "CategoryDao")
@RequestMapping(path = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public interface CategoryApi {
	
	@GetMapping("/categories")
	@Operation(summary = "All categories", description = "List all categories")
	public ResponseEntity<List<CategoryDao>> all();
	
	@GetMapping(path = "/categories/{id}")
	@Operation(summary = "Get one category", description = "Get category matching the given ID")
	public ResponseEntity<CategoryDao> one(@PathVariable("id") Long categoryId) throws NotFoundException;
	
	@PostMapping("/categories")
	@Operation(summary = "new Categoy", description = "submit new  category")
	ResponseEntity<CategoryDao> submitCategorie(@Valid @RequestBody CategoryDao category);
	
	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc);
	

	@DeleteMapping("/categories/{id}")
	@Operation(summary = "Delete category", description = "Delete the category matching the given ID")
	public ResponseEntity<String> deleteCategory(@PathVariable("id") Long catId);
	
	@PutMapping("/categories/{id}")
	@Operation(summary = "Update category", description = "Update the category matching the given ID")
	public ResponseEntity<CategoryDao> updateCategory(@PathVariable("id") Long catId, @RequestBody CategoryDao category, @RequestParam(value = "image", required = false) MultipartFile file) throws NotFoundException;
}
