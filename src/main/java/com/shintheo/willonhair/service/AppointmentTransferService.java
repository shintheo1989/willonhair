package com.shintheo.willonhair.service;

import java.util.List;
import java.util.Optional;

import com.shintheo.willonhair.entity.UserDao;
import com.shintheo.willonhair.entity.AppointmentDao;
import com.shintheo.willonhair.entity.AppointmentTransferDao;

public interface AppointmentTransferService{
	AppointmentTransferDao proceedTransfer(AppointmentDao appointment, UserDao toEmployee);
	
	List<AppointmentTransferDao> findAppointmentTransfers(AppointmentDao appointment);
	
	List<AppointmentTransferDao> getEmployeeReceivedTransfers(UserDao employee);
	
	List<AppointmentTransferDao> getEmployeeMadeTransfers(UserDao employee);
	
	Optional<AppointmentTransferDao> findById(Long appointmentTransferId);
}
