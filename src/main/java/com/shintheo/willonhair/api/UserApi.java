package com.shintheo.willonhair.api;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shintheo.willonhair.entity.UserDao;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "UserDao")
@RequestMapping(value = "users", produces = MediaType.APPLICATION_JSON_VALUE)
public interface UserApi {

	@PostMapping
	@Operation(summary = "new user", description = "register new user")
	ResponseEntity<UserDao> submitUser(@Valid @RequestBody UserDao user);

}
