package com.shintheo.willonhair.serviceImpl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shintheo.willonhair.entity.CategoryDao;
import com.shintheo.willonhair.entity.ServiceDao;
import com.shintheo.willonhair.repository.CategoryRepository;
import com.shintheo.willonhair.repository.ServiceRepository;
import com.shintheo.willonhair.service.ServiceService;

@Service
public class ServiceServiceImpl implements ServiceService {
	@Autowired
	private ServiceRepository serviceRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	// Save operation
	@Override
	public ServiceDao saveService(ServiceDao service) {
		return serviceRepository.save(service);
	}
		
	// Read operation
	@Override
	public List<ServiceDao> fetchServiceList() {
		return (List<ServiceDao>) serviceRepository.findAll();
	}

	@Override
	public List<ServiceDao> fetchCategoryServices(Long catId) {
		// Find category
		CategoryDao cat = categoryRepository.findById(catId).orElseThrow();
		// return cat's services
		return cat.getServices();
	}
	
	@Override
	public Optional<ServiceDao> findById(Long serviceId) {
		return serviceRepository.findById(serviceId);
	}
		
	// Update operation
	@Override
	public ServiceDao updateService(ServiceDao service, Long serviceId) {
		service.setId(serviceId);
		return serviceRepository.save(service);
	}
		
	// Delete operation
	@Override
	public void deleteServiceById(Long serviceId) {
		serviceRepository.deleteById(serviceId);
	}
}
