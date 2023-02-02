package com.shintheo.willonhair.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shintheo.willonhair.entity.fidelity.BonusIncrementDao;
import com.shintheo.willonhair.entity.fidelity.CardDao;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "FidelityCardDao")
@RequestMapping(path = "/api/v1",produces = MediaType.APPLICATION_JSON_VALUE)
public interface FidelityCardApi {
	@PostMapping(path = "/clients/{id}/increment-fidelity")
	@Operation(summary = "Increment fidelity points", description = "Set *value* to add to the fidelity card of a given client")
	public CardDao incrementFidelity(
			@PathVariable(name = "id") Long userId,
			@RequestParam int value);

	@GetMapping(path = "/clients/{id}/bonuses")
	@Operation(summary = "List User Bonuses", description = "List bonuses of a given user")
	public List<BonusIncrementDao> listUserBonuses(@PathVariable(name = "id") Long userId);
}
