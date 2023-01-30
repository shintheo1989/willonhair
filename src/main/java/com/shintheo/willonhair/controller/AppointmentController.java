package com.shintheo.willonhair.controller;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shintheo.willonhair.api.AppointmentApi;
import com.shintheo.willonhair.entity.AppointmentDao;
import com.shintheo.willonhair.entity.AppointmentTransferDao;
import com.shintheo.willonhair.entity.UserDao;
import com.shintheo.willonhair.service.AppointmentService;
import com.shintheo.willonhair.service.AppointmentTransferService;
import com.shintheo.willonhair.service.ClientService;
import com.shintheo.willonhair.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
public class AppointmentController implements AppointmentApi {

	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private AppointmentTransferService transfertService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private EmployeeService employeeService;

	@Override
	public ResponseEntity<AppointmentDao> submitAppointment(@Valid AppointmentDao appointment) {
		log.info("======>RestController: submit({})<======", appointment);
		return ResponseEntity.ok(appointmentService.submitAppointment(appointment));
	}
	
	@Override
	public List<AppointmentDao> all() {
		return appointmentService.fetchAll();
	}
	
	@Override
	public List<AppointmentDao> employeeAppointments(@PathVariable(name = "id") Long employeeId) throws NotFoundException {
		UserDao employee = employeeService.findEmployeeById(employeeId).orElseThrow(() -> new NotFoundException());
		return appointmentService.getEmployeeAppointments(employee);
	}
	
	@Override
	public ResponseEntity<AppointmentDao> createAppointment(
			@PathVariable(name = "id") Long clientId,
			@RequestParam(name = "employeeId", required = true) Long employeeId,
			@ModelAttribute AppointmentDao Appointment) throws NotFoundException {
		// TODO("Only clients should use this api, and he should be the corresponding client")
		// Find client 
		UserDao client = clientService.findClientById(clientId).orElseThrow(() -> new NotFoundException());
		// Find employee
		UserDao employee = employeeService.findEmployeeById(employeeId).orElseThrow(() -> new NotFoundException());
		// Init Appointment
		Appointment.setId(null);
		Appointment.init();
		Appointment.setClient(client);
		Appointment.setEmployee(employee);
		return ResponseEntity.ok(appointmentService.saveAppointment(Appointment));
	}
	
	@Override
	public ResponseEntity<AppointmentDao> createEmployeeCommand(
			@PathVariable(name = "id") Long employeeId,
			@RequestParam(name = "clientId", required = false) Long clientId,
			@ModelAttribute AppointmentDao appointment) throws NotFoundException {
		// TODO("Only employees should use this api, and he should be the corresponding employee")
		// Init Appointment
		appointment.init();
		// Find employee 
		UserDao employee = employeeService.findEmployeeById(employeeId).orElseThrow(() -> new NotFoundException());
		appointment.setEmployee(employee);
		// Find client if clientId is not null
		if(clientId != null) {			
			UserDao client = clientService.findClientById(clientId).orElseThrow(() -> new NotFoundException());			
			appointment.setClient(client);
		}
		return ResponseEntity.ok(appointmentService.saveAppointment(appointment));
	}
	
	@Override
	public ResponseEntity<AppointmentDao> updateAppointment(
			@PathVariable("id") Long appointmentId, 
			@ModelAttribute AppointmentDao Appointment) throws NotFoundException {
		// TODO
		return null;
	}	
	
	@Override
	public ResponseEntity<AppointmentDao> cancelAppointment(@PathVariable("id") Long AppointmentId) throws NotFoundException {
		// TODO("Only the client -owner- can cancel")
		AppointmentDao appointment = appointmentService.findById(AppointmentId).orElseThrow(() -> new NotFoundException());
		// Cancel only if the status is ON_HOLD
		if(!appointment.canCancel()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		// Cancel Appointment;
		appointment.cancel();
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(appointmentService.saveAppointment(appointment));
	}
	
	@Override
	public ResponseEntity<AppointmentDao> startAppointment(@PathVariable("id") Long AppointmentId) throws NotFoundException {
		// TODO("Only the corresponding employee can start")
		AppointmentDao appointment = appointmentService.findById(AppointmentId).orElseThrow(() -> new NotFoundException());

		if(!appointment.canStart()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		// Start Appointment;
		appointment.startWork();
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(appointmentService.saveAppointment(appointment));
	}
	
	@Override
	public ResponseEntity<AppointmentDao> completeAppointment(@PathVariable("id") Long AppointmentId) throws NotFoundException {
		// TODO("Only the corresponding employee can complete")
		AppointmentDao Appointment = appointmentService.findById(AppointmentId).orElseThrow(() -> new NotFoundException());

		if(!Appointment.canComplete()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		// Complete Appointment;
		Appointment.completeWork();
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(appointmentService.saveAppointment(Appointment));
	}	
	
	@Override
	public ResponseEntity<AppointmentDao> payAppointment(@PathVariable("id") Long AppointmentId) throws NotFoundException {
		// TODO("1- Handle access 2-Handle paid amount")
		AppointmentDao Appointment = appointmentService.findById(AppointmentId).orElseThrow(() -> new NotFoundException());

		if(!Appointment.canPay()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		// Pay Appointment;
		Appointment.markAsPaid();
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(appointmentService.saveAppointment(Appointment));
	}
	
	@Override
	public ResponseEntity<List<AppointmentTransferDao>> listAppointmentTransfers(
			@PathVariable("id") Long appointmentId) throws NotFoundException {
		AppointmentDao appointment = appointmentService.findById(appointmentId).orElseThrow();
		return ResponseEntity.ok(transfertService.findAppointmentTransfers(appointment));
	}
	

	@Override
	public ResponseEntity<AppointmentTransferDao> transfertAppointment(
			@PathVariable("id") Long appointmentId,
			@RequestParam(required = true) Long toEmployeeId) throws NotFoundException {
		
		UserDao toEmployee =  employeeService.findEmployeeById(toEmployeeId).orElseThrow();
		AppointmentDao appointment = appointmentService.findById(appointmentId).orElseThrow();
		
		AppointmentTransferDao transfer = transfertService.proceedTransfer(appointment, toEmployee);
		
		return ResponseEntity.ok(transfer);
	}

}
