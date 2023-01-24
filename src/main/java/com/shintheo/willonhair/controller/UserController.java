package com.shintheo.willonhair.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.shintheo.willonhair.api.UserApi;
import com.shintheo.willonhair.entity.UserDao;
import com.shintheo.willonhair.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController implements UserApi {

	private final UserService userService;

	@Override
	public ResponseEntity<UserDao> submitUser(@Valid UserDao user) {
		log.info("======>RestController: submit({})<======", user);
		return ResponseEntity.ok(userService.submitUser(user));
	}

}
