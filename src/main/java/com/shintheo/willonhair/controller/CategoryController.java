package com.shintheo.willonhair.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.shintheo.willonhair.api.CategoryApi;
import com.shintheo.willonhair.entity.CategoryDao;
import com.shintheo.willonhair.service.CategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
public class CategoryController implements CategoryApi {

	private final CategoryService categoryService;

	@Override
	public ResponseEntity<CategoryDao> submitCategorie(@Valid CategoryDao category) {
		log.info("======>RestController: submit categorie<======");
		return ResponseEntity.ok(categoryService.submitCategorie(category));
	}

}