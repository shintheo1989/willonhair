package com.shintheo.willonhair.api;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shintheo.willonhair.entity.CategoryDao;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "CategoryDao")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public interface CategoryApi {
	@PostMapping("/category")
	@Operation(summary = "new Categoy", description = "submit new  category")
	ResponseEntity<CategoryDao> submitCategorie(@Valid @RequestBody CategoryDao category);
}
