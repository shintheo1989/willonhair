package com.shintheo.willonhair.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shintheo.willonhair.api.ServiceApi;
import com.shintheo.willonhair.entity.CategoryDao;
import com.shintheo.willonhair.entity.ServiceDao;
import com.shintheo.willonhair.exception.storage.StorageFileNotFoundException;
import com.shintheo.willonhair.service.CategoryService;
import com.shintheo.willonhair.service.ServiceService;
import com.shintheo.willonhair.service.storage.StorageService;

@RestController
@CrossOrigin
public class ServiceController implements ServiceApi {
	
	private final StorageService storageService;
	
	@Autowired
	ServiceService serviceService;
	
	@Autowired
	CategoryService catService;

	public ServiceController(StorageService storageService) {
		this.storageService = storageService;
	}
	
	@Override
	public ServiceDao one(@PathVariable("id") Long serviceId) throws NotFoundException{
		return serviceService.findById(serviceId).get();
	}

	@Override
	public List<ServiceDao> all() {
		return serviceService.fetchServiceList();
	}

	@Override
	public String deleteService(@PathVariable("id") Long serviceId) {
		serviceService.deleteServiceById(serviceId);
		return "Service deleted";
	}

	@Override
	public ServiceDao updateService(@PathVariable("id") Long serviceId, @ModelAttribute ServiceDao service, @RequestParam(value = "image", required = false) MultipartFile file) throws NotFoundException {
		if(file != null) {
			String newImageName = storageService.storeImage(file);
			service.setImageName(newImageName);
		}
		return serviceService.updateService(service, serviceId);
	}

	@Override
	public ServiceDao createService(
			@RequestParam("name") String name,
			@RequestParam("image") MultipartFile file,
			@RequestParam("price") int price,
			@RequestParam("rangeStart") int rangeStart,
			@RequestParam("rangeEnd") int rangeEnd,
			RedirectAttributes redirectAttributes) {

		String imageName = storageService.storeImage(file);
		ServiceDao service = serviceService.saveService(ServiceDao.builder()
				.name(name)
				.imageName(imageName)
				.price(price)
				.rangeStart(rangeStart)
				.rangeEnd(rangeEnd)
				.build());
		
		redirectAttributes.addFlashAttribute("message",
				"Service created succesfully!");

		return service;
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}
	
	/**
	 * Handle CRUD on categories
	 */

	@Override
	public List<CategoryDao> getServiceCategories(@PathVariable(name = "id") Long serviceId) throws NotFoundException {
		return catService.getServiceCategories(serviceId);
	}

	@Override
	public CategoryDao createCategory(@RequestParam("name") String name,
			@RequestParam("image") MultipartFile file,
			@RequestParam(name = "note", required = false) String note,
			@PathVariable(name = "id") Long serviceId,
			RedirectAttributes redirectAttributes) throws NotFoundException{
		
		ServiceDao service = serviceService.findById(serviceId).orElseThrow(() -> new NotFoundException());

		String imageName = storageService.storeImage(file);
		CategoryDao cat = catService.saveCategory(CategoryDao.builder()
				.name(name)
				.imageName(imageName)
				.note(note)
				.service(service)
				.build());
		
		redirectAttributes.addFlashAttribute("message",
				"Service created succesfully!");

		return cat;
	}

	@Override
	public String deleteCategory(@PathVariable("id") Long serviceId, @PathVariable("catId") Long categoryId) {
		catService.deleteCategoryById(categoryId);
		return "Category deleted";
	}

	@Override
	public CategoryDao updateCategory(@PathVariable("id") Long serviceId, @PathVariable("catId") Long categoryId, @ModelAttribute CategoryDao category, @RequestParam(value = "image", required = false) MultipartFile file) throws NotFoundException {
		if(file != null) {
			String newImageName = storageService.storeImage(file);
			category.setImageName(newImageName);
		}
		return catService.updateCategory(category, categoryId);
	}

	@Override
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"").body(file);
	}
}
