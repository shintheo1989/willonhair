package com.shintheo.willonhair.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shintheo.willonhair.api.ServiceApi;
import com.shintheo.willonhair.entity.CategoryDao;
import com.shintheo.willonhair.entity.ServiceDao;
import com.shintheo.willonhair.service.CategoryService;
import com.shintheo.willonhair.service.ServiceService;
import com.shintheo.willonhair.service.storage.StorageService;

@RestController
@CrossOrigin
public class ServiceController implements ServiceApi {
	
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private ServiceService serviceService;
	
	@Autowired
	private CategoryService catService;
	
	@Override
	public ResponseEntity<ServiceDao> one(Long categoryId, Long serviceId) throws NotFoundException {
		return ResponseEntity.ok(serviceService.findById(serviceId).get());
	}

	@Override
	public ResponseEntity<List<ServiceDao>> all() {
		return ResponseEntity.ok(serviceService.fetchServiceList());
	}

	@Override
	public ResponseEntity<List<ServiceDao>> getCategoriesServices(Long catId) throws NotFoundException {
		return ResponseEntity.ok(serviceService.fetchCategoryServices(catId));
	}

	@Override
	public ResponseEntity<ServiceDao> createService(
			@PathVariable("id") Long categoryId,
			@RequestBody ServiceDao data,
			@RequestParam(name = "image", required = false) MultipartFile file,
			RedirectAttributes redirectAttributes) throws NotFoundException {
		
		CategoryDao category = catService.findById(categoryId).orElseThrow(() -> new NotFoundException());

		var imageName = "";
		if(file != null) {
			imageName = storageService.storeImage(file);
		}
		
		data.setId(null);
		data.setPictureFullPath(imageName);
		data.setCategory(category);
		
		ServiceDao service = serviceService.saveService(data);
		
		redirectAttributes.addFlashAttribute("message",
				"Service created succesfully!");

		return ResponseEntity.ok(service);
	}

	@Override
	public ResponseEntity<String> deleteService(Long categoryId, Long serviceId) {
		serviceService.deleteServiceById(serviceId);
		return ResponseEntity.ok("Service deleted");
	}

	@Override
	public ResponseEntity<ServiceDao> updateService(
			@PathVariable("id") Long categoryId, 
			@PathVariable("servId") Long serviceId, 
			@RequestBody ServiceDao service, 
			@RequestParam(value = "image", required = false) MultipartFile file) throws NotFoundException {
		if(file != null) {
			String newImageName = storageService.storeImage(file);
			service.setPictureFullPath(newImageName);
		}
		return ResponseEntity.ok(serviceService.updateService(service, serviceId));
	}
}
