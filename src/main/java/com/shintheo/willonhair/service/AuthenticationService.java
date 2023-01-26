package com.shintheo.willonhair.service;

import com.shintheo.willonhair.base.Status;
import com.shintheo.willonhair.base.Type;
import com.shintheo.willonhair.model.AuthenticationRequest;
import com.shintheo.willonhair.model.AuthenticationResponse;
import com.shintheo.willonhair.model.RegisterRequest;

public interface AuthenticationService {
	
	public AuthenticationResponse register(RegisterRequest request, Type type, Status status);
	
	public AuthenticationResponse authenticate(AuthenticationRequest request);
}
