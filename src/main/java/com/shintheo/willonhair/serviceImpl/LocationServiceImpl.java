package com.shintheo.willonhair.serviceImpl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shintheo.willonhair.entity.LocationDao;
import com.shintheo.willonhair.repository.LocationRepository;
import com.shintheo.willonhair.service.LocationService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository locationRepo;

	@Override
	public LocationDao createLocation(LocationDao location) {
		log.info("======>Location: submit location({})<======", location);
		return locationRepo.save(location);
	}
	
	@Override
	public LocationDao saveLocation(LocationDao Location) {
		return locationRepo.save(Location);
	}
			
	@Override
	public List<LocationDao> fetchAll(){
		return locationRepo.findAll();
	}
		
	@Override
	public Optional<LocationDao> findById(Long LocationId) {
		return locationRepo.findById(LocationId);
	}
			
	@Override
	public LocationDao updateLocation(LocationDao Location, Long catId) {
		Location.setId(catId);
		return locationRepo.save(Location);
	}
			
	@Override
	public void deleteLocationById(Long locationId) {
		locationRepo.deleteById(locationId);
	}

}
