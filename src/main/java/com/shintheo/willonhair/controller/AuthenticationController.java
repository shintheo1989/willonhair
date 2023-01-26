package com.shintheo.willonhair.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.shintheo.willonhair.api.AuthenticationApi;
import com.shintheo.willonhair.base.Status;
import com.shintheo.willonhair.base.Type;
import com.shintheo.willonhair.model.AuthenticationRequest;
import com.shintheo.willonhair.model.AuthenticationResponse;
import com.shintheo.willonhair.model.RegisterRequest;
import com.shintheo.willonhair.service.AuthenticationService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationApi {
	
	private final AuthenticationService service;

	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
		return ResponseEntity.ok(service.register(request, Type.CUSTOMER, Status.VISIBLE));
	}

	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
		return ResponseEntity.ok(service.authenticate(request));
	}
}
