package com.shintheo.willonhair.api;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shintheo.willonhair.entity.ExtrasDao;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "ExtrasDao")
@RequestMapping(value = MediaType.APPLICATION_JSON_VALUE)
public interface ExtrasApi {
	@PostMapping("/extras")
	@Operation(summary = "new extras", description = "submit new  extras")
	ResponseEntity<ExtrasDao> submitExtras(@Valid @RequestBody ExtrasDao extras);
}