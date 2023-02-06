package com.shintheo.willonhair.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.shintheo.willonhair.api.CustomerApi;
import com.shintheo.willonhair.entity.UserDao;
import com.shintheo.willonhair.service.ClientService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
public class CustomerController implements CustomerApi {

	@Autowired
	private ClientService clientService;	
	
	@Override
	public ResponseEntity<List<UserDao>> all() {
		log.info("Return list of customers");
		return ResponseEntity.ok(clientService.fetchAll());
	}	
}
