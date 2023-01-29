package com.shintheo.willonhair.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shintheo.willonhair.entity.AppointmentDao;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "AppointmentDao")
@RequestMapping(path = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public interface AppointmentApi {
	ResponseEntity<AppointmentDao> submitAppointment(@Valid @RequestBody AppointmentDao appointment);
	
	@GetMapping(path = "/appointments")
	public List<AppointmentDao> all();
	
	@GetMapping(path = "/employees/{id}/appointments")
	public List<AppointmentDao> employeeAppointments(@PathVariable(name = "id") Long employeeId) throws NotFoundException;
	
	@PostMapping(path = "/clients/{id}/appointments")
	public ResponseEntity<AppointmentDao> createAppointment(
			@PathVariable(name = "id") Long clientId,
			@RequestParam(name = "employeeId", required = true) Long employeeId,
			@ModelAttribute AppointmentDao appointment) throws NotFoundException;
	
	@PostMapping(path = "/employees/{id}/appointments")
	public ResponseEntity<AppointmentDao> createEmployeeCommand(
			@PathVariable(name = "id") Long employeeId,
			@RequestParam(name = "clientId", required = false) Long clientId,
			@ModelAttribute AppointmentDao appointment) throws NotFoundException;
	
	@PutMapping(path = "/appointments/{id}")
	public ResponseEntity<AppointmentDao> updateAppointment(
			@PathVariable("id") Long appointmentId, 
			@ModelAttribute AppointmentDao appointment) throws NotFoundException ;
	
	@PutMapping(path = "/appointments/{id}/cancel")
	public ResponseEntity<AppointmentDao> cancelAppointment(@PathVariable("id") Long appointmentId) throws NotFoundException;	
	
	@PutMapping(path = "/appointments/{id}/start")
	public ResponseEntity<AppointmentDao> startAppointment(@PathVariable("id") Long appointmentId) throws NotFoundException;	
	
	@PutMapping(path = "/appointments/{id}/complete")
	public ResponseEntity<AppointmentDao> completeAppointment(@PathVariable("id") Long appointmentId) throws NotFoundException;
	
	@PutMapping(path = "/appointments/{id}/pay")
	public ResponseEntity<AppointmentDao> payAppointment(@PathVariable("id") Long appointmentId) throws NotFoundException;
}
