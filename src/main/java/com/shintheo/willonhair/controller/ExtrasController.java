package com.shintheo.willonhair.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.shintheo.willonhair.api.ExtrasApi;
import com.shintheo.willonhair.entity.ExtrasDao;
import com.shintheo.willonhair.service.ExtrasService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
@RequiredArgsConstructor
public class ExtrasController implements ExtrasApi {

	@Autowired
	private ExtrasService extrasService;

	@Override
	public ResponseEntity<ExtrasDao> submitExtras(@Valid ExtrasDao extras) {
		log.info("=========>Conntroller: submùit extras <========");
		return ResponseEntity.ok(extrasService.submitExtra(extras));
	}

}
