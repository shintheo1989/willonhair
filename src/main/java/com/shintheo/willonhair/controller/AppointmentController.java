package com.shintheo.willonhair.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.shintheo.willonhair.api.AppointmentApi;
import com.shintheo.willonhair.entity.AppointmentDao;
import com.shintheo.willonhair.service.AppointmentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
public class AppointmentController implements AppointmentApi {

	private final AppointmentService appointmentService;

	@Override
	public ResponseEntity<AppointmentDao> submitAppointment(@Valid AppointmentDao appointment) {
		log.info("======>RestController: submit({})<======", appointment);
		return ResponseEntity.ok(appointmentService.submitAppointment(appointment));
	}

}
