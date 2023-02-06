package com.shintheo.willonhair.controller.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<SettingsDao> updateFidelityPointToBonus(@RequestParam(name = "value") String value) {
		SettingsDao settings = SettingsDao.buildPointsToBonusSettings();
		settings.setSettingValue(value);
		return ResponseEntity.ok(service.updateSettings(settings));
	}

	@Override
	public ResponseEntity<List<SettingsDao>> getSettings() {
		return ResponseEntity.ok(service.fetchAll());
	}
}
