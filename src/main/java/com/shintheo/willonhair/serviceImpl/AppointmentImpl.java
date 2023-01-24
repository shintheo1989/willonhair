package com.shintheo.willonhair.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shintheo.willonhair.entity.AppointmentDao;
import com.shintheo.willonhair.repository.AppointmentRepository;
import com.shintheo.willonhair.service.AppointmentService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AppointmentImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Override
	public AppointmentDao submitAppointment(AppointmentDao appointment) {
		log.info("======>Service: submit appointment({})<======", appointment);
		return appointmentRepository.save(appointment);
	}

}
