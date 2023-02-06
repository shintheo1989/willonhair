package com.shintheo.willonhair.service.config;

import java.util.List;

import com.shintheo.willonhair.entity.config.SettingsDao;

public interface SettingsService {

	SettingsDao updateSettings(SettingsDao settings);
	
	List<SettingsDao> fetchAll();
}
