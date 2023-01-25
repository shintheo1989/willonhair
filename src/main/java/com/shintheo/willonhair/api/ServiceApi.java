package com.shintheo.willonhair.api;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shintheo.willonhair.entity.CategoryDao;
import com.shintheo.willonhair.entity.ServiceDao;
import com.shintheo.willonhair.exception.storage.StorageFileNotFoundException;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "ServiceDao")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public interface ServiceApi {	
	
	@GetMapping(path = "/api/services/{id}")
	public ServiceDao one(@PathVariable("id") Long serviceId) throws NotFoundException;
	
	@GetMapping("/api/services")
	public List<ServiceDao> all();
	
	@DeleteMapping("/api/services/{id}")
	public String deleteService(@PathVariable("id") Long serviceId);
	
	@PutMapping("/api/services/{id}")
	public ServiceDao updateService(@PathVariable("id") Long serviceId, @ModelAttribute ServiceDao service, @RequestParam(value = "image", required = false) MultipartFile file) throws NotFoundException;
	

	@PostMapping("/api/services")
	public ServiceDao createService(
			@RequestParam("name") String name,
			@RequestParam("image") MultipartFile file,
			@RequestParam("price") int price,
			@RequestParam("rangeStart") int rangeStart,
			@RequestParam("rangeEnd") int rangeEnd,
			RedirectAttributes redirectAttributes) ;
	
	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc);
	
	/**
	 * Handle CRUD on categories
	 */
	
	@GetMapping("/api/services/{id}/categories")
	public List<CategoryDao> getServiceCategories(@PathVariable(name = "id") Long serviceId) throws NotFoundException;
	
	@PostMapping("/api/services/{id}/categories")
	public CategoryDao createCategory(@RequestParam("name") String name,
			@RequestParam("image") MultipartFile file,
			@RequestParam(name = "note", required = false) String note,
			@PathVariable(name = "id") Long serviceId,
			RedirectAttributes redirectAttributes) throws NotFoundException;
	
	@DeleteMapping("/api/services/{id}/categories/{catId}")
	public String deleteCategory(@PathVariable("id") Long serviceId, @PathVariable("catId") Long categoryId);
	
	@PutMapping("/api/services/{id}/categories/{catId}")
	public CategoryDao updateCategory(@PathVariable("id") Long serviceId, @PathVariable("catId") Long categoryId, @ModelAttribute CategoryDao category, @RequestParam(value = "image", required = false) MultipartFile file) throws NotFoundException;
	
	@GetMapping("/assets/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename);

}
