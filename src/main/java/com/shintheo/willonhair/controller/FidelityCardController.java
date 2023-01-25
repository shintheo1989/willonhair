package com.shintheo.willonhair.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.shintheo.willonhair.api.FidelityCardApi;
import com.shintheo.willonhair.entity.EmployeeDao;
import com.shintheo.willonhair.entity.UserDao;
import com.shintheo.willonhair.entity.fidelity.BonusIncrementDao;
import com.shintheo.willonhair.entity.fidelity.CardDao;
import com.shintheo.willonhair.service.EmployeeService;
import com.shintheo.willonhair.service.fidelity.CardService;

import lombok.NoArgsConstructor;

@RestController
@CrossOrigin
@NoArgsConstructor
public class FidelityCardController implements FidelityCardApi {
	@Autowired
    CardService fidelityCardSrv;
	
	@Autowired EmployeeService employeeService;
	
	@Override
	public CardDao incrementFidelity(@PathVariable(name = "id") Long userId) {
		EmployeeDao employee = employeeService.findEmployeeById(userId).orElseThrow(() -> new EntityNotFoundException());
		// Init card in case it is not done yet
		fidelityCardSrv.initUserCard(employee.getUser());
		// Increment points
		return fidelityCardSrv.incrementUserFidelityPoint(employee.getUser());		
	}
	
	@Override
	public List<BonusIncrementDao> listUserBonuses(@PathVariable(name = "id") Long userId) {
		UserDao user = employeeService.findUserById(userId).orElseThrow(() -> new EntityNotFoundException());
		CardDao card = fidelityCardSrv.getUserCard(user);
		return card.getBonuses();
	}
}
