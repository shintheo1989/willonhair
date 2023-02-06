package com.shintheo.willonhair.serviceImpl.config;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shintheo.willonhair.entity.config.SettingsDao;
import com.shintheo.willonhair.repository.config.SettingsRepository;
import com.shintheo.willonhair.service.config.SettingsService;

@Service
public class SettingsServiceImpl implements SettingsService{
	@Autowired
	SettingsRepository repo;
	
	@Override
	public SettingsDao updateSettings(SettingsDao data) {
		System.out.print("Search values" + data.getSectionName() + " " + data.getSettingName() + "\n");
		
		// Find setting
		Optional<SettingsDao> opSettings = repo.findBySectionNameAndSettingName(data.getSectionName(), data.getSettingName());

		if(opSettings.isPresent()) {
			// Update settings
			SettingsDao dbSettings = opSettings.get();
			
			dbSettings.setSettingValue(data.getSettingValue());
			dbSettings.setSettingType(data.getSettingType());
			return repo.save(dbSettings);
		} else {
			// Create new settings
			return repo.save(data); 
		}
	}

	@Override
	public List<SettingsDao> fetchAll() {
		return repo.findAll();
	}

}