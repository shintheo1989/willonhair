package com.shintheo.willonhair.api;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shintheo.willonhair.entity.AppointmentDao;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "AppointmentDao")
@RequestMapping(value = MediaType.APPLICATION_JSON_VALUE)
public interface AppointmentApi {
	ResponseEntity<AppointmentDao> submitAppointment(@Valid @RequestBody AppointmentDao appointment);
}
