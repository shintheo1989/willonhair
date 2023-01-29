package com.shintheo.willonhair.service;

import java.util.List;
import java.util.Optional;

import com.shintheo.willonhair.entity.LocationDao;

public interface LocationService {
	LocationDao createLocation(LocationDao location);
	
	LocationDao saveLocation(LocationDao location);
	
	Optional<LocationDao> findById(Long categoryId);
	
	List<LocationDao> fetchAll();
	
	LocationDao updateLocation(LocationDao location, Long locationId);
	
	void deleteLocationById(Long locationId);
}