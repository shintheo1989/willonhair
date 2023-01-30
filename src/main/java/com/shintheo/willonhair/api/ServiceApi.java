package com.shintheo.willonhair.api;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shintheo.willonhair.entity.ServiceDao;
import com.shintheo.willonhair.entity.ServiceProviderDao;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "ServiceDao")
@RequestMapping(path = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public interface ServiceApi {	

	@GetMapping("/services")
	@Operation(summary = "List services", description = "List all services")
	public ResponseEntity<List<ServiceDao>> all();
	
	@GetMapping("/categories/{id}/services")
	@Operation(summary = "List category's services", description = "List all services in a given category")
	public ResponseEntity<List<ServiceDao>> getCategoriesServices(@PathVariable(name = "id") Long catId) throws NotFoundException;

	@GetMapping(path = "/categories/{catId}/services/{id}")
	@Operation(summary = "Get one service", description = "Get one service corresponding to the given id (in a given category)")
	public ResponseEntity<ServiceDao> one(@PathVariable("catId") Long categoryId, @PathVariable("id") Long serviceId) throws NotFoundException;
	 
	@PostMapping("/categories/{id}/services")
	@Operation(summary = "Create one", description = "Create a new service")
	public ResponseEntity<ServiceDao> createService(
			@PathVariable("id") Long categoryId,
			@RequestBody ServiceDao data,
			@RequestParam(name = "image", required = false) MultipartFile file,
			RedirectAttributes redirectAttributes) throws NotFoundException;
	
	@PutMapping("/categories/{id}/services/{servId}")
	@Operation(summary = "Update service", description = "Update a service corresponding to a given id")
	public ResponseEntity<ServiceDao> updateService(@PathVariable("id") Long categoryId, @PathVariable("servId") Long serviceId, @RequestBody ServiceDao service, @RequestParam(value = "image", required = false) MultipartFile file) throws NotFoundException;

	@DeleteMapping("/categories/{id}/services/{servId}")
	@Operation(summary = "Delete one", description = "The the service corresponding to the given id")
	public ResponseEntity<String> deleteService(@PathVariable("id") Long categoryId, @PathVariable("servId") Long serviceId);
	
	@GetMapping("/services/{id}/providers")
	@Operation(summary = "List service's providers", description = "List providers of a given service")
	public ResponseEntity<List<ServiceProviderDao>> getServiceProviders(@PathVariable("id") Long serviceId);
	
	@PostMapping("/services/{id}/providers")
	@Operation(summary = "Add provider", description = "Add provider to a given service")
	public ResponseEntity<ServiceProviderDao> addServiceProviders(
			@PathVariable("id") Long serviceId,
			@RequestParam(name = "userId", required = true) Long userId,
			@ModelAttribute ServiceProviderDao serviceProvider);
	
	@PutMapping("/services/{id}/providers/{serviceProviderId}")
	@Operation(summary = "Add provider", description = "Add provider to a given service")
	public ResponseEntity<ServiceProviderDao> updateServiceProviders(
			@PathVariable("id") Long serviceId,
			@PathVariable("serviceProviderId") Long serviceProviderId,
			@ModelAttribute ServiceProviderDao serviceProvider);
	
	@DeleteMapping("/services/{id}/providers/{providerId}")
	@Operation(summary = "Remove provider", description = "Remove a provider off a given service")
	public ResponseEntity<String> removeServiceProviders(
			@PathVariable("id") Long serviceId,
			@PathVariable("providerId") Long providerId);
}
