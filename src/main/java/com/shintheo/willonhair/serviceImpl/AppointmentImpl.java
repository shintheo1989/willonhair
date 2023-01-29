package com.shintheo.willonhair.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shintheo.willonhair.entity.AppointmentDao;
import com.shintheo.willonhair.entity.UserDao;
import com.shintheo.willonhair.repository.AppointmentRepository;
import com.shintheo.willonhair.service.AppointmentService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AppointmentImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository repo;

	@Override
	public AppointmentDao submitAppointment(AppointmentDao appointment) {
		log.info("======>Service: submit appointment({})<======", appointment);
		return repo.save(appointment);
	}

	@Override
	public AppointmentDao saveAppointment(AppointmentDao appointment) {
		return repo.save(appointment);
	}

	@Override
	public List<AppointmentDao> fetchAll() {
		return repo.findAll();
	}

	@Override
	public List<AppointmentDao> getEmployeeAppointments(UserDao employee) {
		return repo.findByEmployee(employee);
	}

	@Override
	public Optional<AppointmentDao> findById(Long appointmentId) {
		return repo.findById(appointmentId);
	}

	@Override
	public AppointmentDao updateAppointment(AppointmentDao appointment, Long appointmentId) {
		return repo.save(appointment);
	}

	@Override
	public void deleteAppointmentById(Long appointmentId) {
		repo.deleteById(appointmentId);
	}

}
