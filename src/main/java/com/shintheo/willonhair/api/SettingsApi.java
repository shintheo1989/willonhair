package com.shintheo.willonhair.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shintheo.willonhair.entity.config.SettingsDao;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "SettingDao")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public interface SettingsApi {
	@PutMapping(path = "/api/settings/fidelity-card/points-to-bonus")
	@Operation(summary = "Point to bonuses", description = "Update equivalence between points and bonuses. How many points "
			+ "do we need to have a bonus")
	public SettingsDao updateFidelityPointToBonus(@RequestParam(name = "value") String value);
}
