package com.shintheo.willonhair.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shintheo.willonhair.entity.LocationDao;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "LocationDao")
@RequestMapping(path = "/api/v1/locations", produces = MediaType.APPLICATION_JSON_VALUE)
public interface LocationApi {
	
	@GetMapping("")
	@Operation(summary = "All locations", description = "List all locations")
	public ResponseEntity<List<LocationDao>> all();
	
	@GetMapping(path = "/{id}")
	@Operation(summary = "Get one location", description = "Get location matching the given ID")
	public ResponseEntity<LocationDao> one(@PathVariable("id") Long locationId) throws NotFoundException;
	
	@PostMapping("")
	@Operation(summary = "new location", description = "submit new  location")
	public ResponseEntity<LocationDao> createLocations(
			@Valid @ModelAttribute LocationDao Location, 
			@RequestParam(required = false) MultipartFile file);

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete location", description = "Delete the location matching the given ID")
	public ResponseEntity<String> deleteLocation(@PathVariable("id") Long catId);
	
	@PutMapping("/{id}")
	@Operation(summary = "Update location", description = "Update the location matching the given ID")
	public ResponseEntity<LocationDao> updateLocation(
			@PathVariable("id") Long catId,
			@Valid @ModelAttribute LocationDao Location, 
			@RequestParam(required = false) MultipartFile file) throws NotFoundException;
}
