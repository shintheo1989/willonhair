package com.shintheo.willonhair.service;

import com.shintheo.willonhair.entity.AppointmentDao;

public interface AppointmentService {
	AppointmentDao submitAppointment(AppointmentDao appointment);
}