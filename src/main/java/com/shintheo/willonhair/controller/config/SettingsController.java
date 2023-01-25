package com.shintheo.willonhair.controller.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shintheo.willonhair.api.SettingsApi;
import com.shintheo.willonhair.entity.config.SettingsDao;
import com.shintheo.willonhair.service.config.SettingsService;

import lombok.NoArgsConstructor;

@RestController
@CrossOrigin
@NoArgsConstructor
public class SettingsController implements SettingsApi{
	@Autowired
	SettingsService service;

	@Override
	public SettingsDao updateFidelityPointToBonus(@RequestParam(name = "value") String value) {
		SettingsDao settings = SettingsDao.buildPointsToBonusSettings();
		settings.setSettingValue(value);
		return service.updateSettings(settings);
	}
}
