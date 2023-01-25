package com.shintheo.willonhair.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shintheo.willonhair.entity.config.SettingsDao;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "SettingDao")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public interface SettingsApi {
	@PutMapping(path = "/api/settings/fidelity-card/points-to-bonus")
	public SettingsDao updateFidelityPointToBonus(@RequestParam(name = "value") String value);
}
