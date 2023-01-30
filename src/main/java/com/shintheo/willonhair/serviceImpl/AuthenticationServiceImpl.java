package com.shintheo.willonhair.serviceImpl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shintheo.willonhair.base.Status;
import com.shintheo.willonhair.base.Type;
import com.shintheo.willonhair.entity.UserDao;
import com.shintheo.willonhair.model.AuthenticationRequest;
import com.shintheo.willonhair.model.AuthenticationResponse;
import com.shintheo.willonhair.model.RegisterRequest;
import com.shintheo.willonhair.repository.UserRepository;
import com.shintheo.willonhair.service.AuthenticationService;
import com.shintheo.willonhair.service.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
	
	private final UserRepository userRepo;
	
	private final PasswordEncoder passwordEncoder;
	
	private final JwtService jwtService;
	
	private final AuthenticationManager authenticationManager;
	
	@Override
	public AuthenticationResponse register(RegisterRequest request, Type type, Status status) {
		var user = UserDao.builder()
				.firstName(request.getFirstname())
				.lastName(request.getLastname())
				.email(request.getEmail())
				.gender(request.getGender())
				.password(passwordEncoder.encode(request.getPassword()))
				.phone(request.getPhone())
				.type(type)
				.status(status)
				.build();
		var saveUser = userRepo.save(user);
		
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.user(saveUser)
				.build();
	}
	
	@Override
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(), 
						request.getPassword()
						)
				);
		var user = userRepo.findByEmail(request.getEmail())
				.orElseThrow();
		
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.user(user)
				.build();
	}
}
