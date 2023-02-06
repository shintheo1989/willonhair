package com.shintheo.willonhair.controller;

import java.util.ArrayList;
import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shintheo.willonhair.api.LocationApi;
import com.shintheo.willonhair.entity.LocationDao;
import com.shintheo.willonhair.entity.UserDao;
import com.shintheo.willonhair.service.LocationService;
import com.shintheo.willonhair.service.UserService;
import com.shintheo.willonhair.service.storage.StorageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
public class LocationController implements LocationApi {

	@Autowired
	private final LocationService service;
	
	@Autowired
	private final StorageService storageService;
	
	@Autowired
	private final UserService userService;

	@Override
	public ResponseEntity<LocationDao> createLocations(
			@Valid @ModelAttribute LocationDao location,
			@RequestParam(required = false) MultipartFile file) {
		log.info("======>RestController: submit Locations<======");
		if(file != null) {
			try {
				
				String newImageName = storageService.storeImage(file);
				location.setPictureFullPath(newImageName);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		// Handle amelia ID
		if(location.getId() != null) {
			location.transferIdToAmeliaId();
		}
		return ResponseEntity.ok(service.createLocation(location));
	}

	@Override
	public ResponseEntity<List<LocationDao>> all() {
		return ResponseEntity.ok(service.fetchAll());
	}

	@Override
	public ResponseEntity<LocationDao> one(Long LocationId) throws NotFoundException {
		return ResponseEntity.ok(service.findById(LocationId).orElseThrow());
	}

	@Override
	public ResponseEntity<String> deleteLocation(Long catId) {
		service.deleteLocationById(catId);
		return ResponseEntity.ok("Location Deleted");
	}

	@Override
	public ResponseEntity<LocationDao> updateLocation(
			Long catId,
			@Valid @ModelAttribute LocationDao location,
			@RequestParam(required = false) MultipartFile file) throws NotFoundException {
		if(file != null) {
			String newImageName = storageService.storeImage(file);
			location.setPictureFullPath(newImageName);
		}
		return ResponseEntity.ok(service.updateLocation(location, catId));
	}
	
	
	public ResponseEntity<List<LocationDao>> userLocations(
			@PathVariable(name = "id") Long userId) {
		UserDao user = userService.getUserById(userId);
		return ResponseEntity.ok(user.getLocations());
	}
	
	@Override
	public ResponseEntity<List<LocationDao>> linkUserToLocations(
			@PathVariable(name = "id") Long userId,
			@RequestParam(required = true) Long[] locationIds) {
		UserDao user = userService.getUserById(userId);
		List<LocationDao> locations = new ArrayList<>();
		for(Long id: locationIds) {
			try {				
				LocationDao location = service.findById(id).orElseThrow();
				locations.add(location);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		user.setLocations(locations);
		userService.updateUser(user);
		return ResponseEntity.ok(user.getLocations());
	}

}