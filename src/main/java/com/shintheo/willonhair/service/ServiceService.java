package com.shintheo.willonhair.service;

import java.util.List;
import java.util.Optional;

import com.shintheo.willonhair.entity.ServiceDao;
import com.shintheo.willonhair.entity.ServiceProviderDao;

public interface ServiceService {
	
	ServiceDao saveService(ServiceDao service);
	
	List<ServiceDao> fetchServiceList();
	
	List<ServiceDao> fetchCategoryServices(Long catId);
	
	Optional<ServiceDao> findById(Long serviceId);
	
	ServiceDao updateService(ServiceDao service, Long serviceId);
	
	void deleteServiceById(Long serviceId);

	ServiceProviderDao createServiceProvider(ServiceProviderDao provider);
	
	ServiceProviderDao updateServiceProvider(ServiceProviderDao provider);

	List<ServiceProviderDao> fetchServiceProviders(Long serviceId);
	
	ServiceProviderDao findServiceProviderById(Long serviceProviderId);
		
	void deleteServiceProvider(Long serviceProviderId);
}