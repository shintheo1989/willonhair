package com.shintheo.willonhair.service;

import java.util.List;
import java.util.Optional;

import com.shintheo.willonhair.entity.AppointmentDao;
import com.shintheo.willonhair.entity.UserDao;

public interface AppointmentService {
	AppointmentDao submitAppointment(AppointmentDao appointment);
	// Save operation
	AppointmentDao saveAppointment(AppointmentDao appointment);
		
	// List all appointments
	List<AppointmentDao> fetchAll();
	
	// List employee's appointments
	List<AppointmentDao> getEmployeeAppointments(UserDao employee);
	
	Optional<AppointmentDao> findById(Long appointmentId);
	
	// Update operation
	AppointmentDao updateAppointment(AppointmentDao appointment, Long appointmentId);
	
	// Delete operation
	void deleteAppointmentById(Long appointmentId);
}