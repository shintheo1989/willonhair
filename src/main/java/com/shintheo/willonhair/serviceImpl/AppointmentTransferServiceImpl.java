package com.shintheo.willonhair.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shintheo.willonhair.entity.UserDao;
import com.shintheo.willonhair.repository.AppointmentRepository;
import com.shintheo.willonhair.repository.AppointmentTransferRepository;
import com.shintheo.willonhair.service.AppointmentTransferService;
import com.shintheo.willonhair.entity.AppointmentDao;
import com.shintheo.willonhair.entity.AppointmentTransferDao;

@Service
public class AppointmentTransferServiceImpl implements AppointmentTransferService{
	
	@Autowired
	private AppointmentTransferRepository repo;
	
	@Autowired
	private AppointmentRepository appointmentRepo;

	@Override
	public AppointmentTransferDao proceedTransfer(AppointmentDao appointment, UserDao toEmployee) {
		if(appointment.getEmployee().getId() != toEmployee.getId()) {
			// Proceed transfer
			AppointmentTransferDao transfert = AppointmentTransferDao.builder()
					.appointment(appointment)
					.fromEmployee(appointment.getEmployee())
					.toEmployee(toEmployee)
					.build();
			appointment.setEmployee(toEmployee);
			appointmentRepo.save(appointment);
			return repo.save(transfert);
		}
		return null;
	}

	@Override
	public List<AppointmentTransferDao> findAppointmentTransfers(AppointmentDao appointment) {
		return repo.findByAppointment(appointment);
	}

	@Override
	public List<AppointmentTransferDao> getEmployeeReceivedTransfers(UserDao employee) {
		return repo.findByToEmployee(employee);
	}

	@Override
	public List<AppointmentTransferDao> getEmployeeMadeTransfers(UserDao employee) {
		return repo.findByFromEmployee(employee);
	}

	@Override
	public Optional<AppointmentTransferDao> findById(Long appointmentTransferId) {
		return repo.findById(appointmentTransferId);
	}
	
}
