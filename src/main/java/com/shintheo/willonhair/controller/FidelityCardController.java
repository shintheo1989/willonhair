package com.shintheo.willonhair.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.shintheo.willonhair.api.FidelityCardApi;
import com.shintheo.willonhair.entity.UserDao;
import com.shintheo.willonhair.entity.fidelity.BonusIncrementDao;
import com.shintheo.willonhair.entity.fidelity.CardDao;
import com.shintheo.willonhair.service.UserService;
import com.shintheo.willonhair.service.fidelity.CardService;

import lombok.NoArgsConstructor;

@RestController
@CrossOrigin
@NoArgsConstructor
public class FidelityCardController implements FidelityCardApi {
	@Autowired
    CardService fidelityCardSrv;
	
	@Autowired UserService userService;
	
	@Override
	public CardDao incrementFidelity(@PathVariable(name = "id") Long userId) {
		UserDao user = userService.getUserById(userId);
		// Init card in case it is not done yet
		fidelityCardSrv.initUserCard(user);
		// Increment points
		return fidelityCardSrv.incrementUserFidelityPoint(user);		
	}
	
	@Override
	public List<BonusIncrementDao> listUserBonuses(@PathVariable(name = "id") Long userId) {
		UserDao user = userService.getUserById(userId);
		CardDao card = fidelityCardSrv.getUserCard(user);
		return card.getBonuses();
	}
}
