package com.shintheo.willonhair.service;

import java.util.List;
import java.util.Optional;

import com.shintheo.willonhair.entity.ServiceDao;

public interface ServiceService {
	// Save operation
	ServiceDao saveService(ServiceDao service);
	
	// Read operation
	List<ServiceDao> fetchServiceList();
	
	Optional<ServiceDao> findById(Long serviceId);
	
	// Update operation
	ServiceDao updateService(ServiceDao service, Long serviceId);
	
	// Delete operation
	void deleteServiceById(Long serviceId);
}