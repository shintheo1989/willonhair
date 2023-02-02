package com.shintheo.willonhair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shintheo.willonhair.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Tag(name = "Reset Password")
public class ResetPassWordController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.PUT, value = "/reset/password")
	@Operation(summary = "Reset user Password")
	public String resetForgotPassword(@RequestParam(name = "emailAddress") String emailAddress) {
		log.info("=======>Controller: Reset password ({}) <======",emailAddress);
		userService.resetPassword(emailAddress);
		return "ok";
	}
}
