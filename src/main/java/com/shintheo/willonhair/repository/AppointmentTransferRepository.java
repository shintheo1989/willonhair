package com.shintheo.willonhair.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shintheo.willonhair.entity.AppointmentDao;
import com.shintheo.willonhair.entity.AppointmentTransferDao;
import com.shintheo.willonhair.entity.UserDao;

public interface AppointmentTransferRepository extends JpaRepository<AppointmentTransferDao, Long> {
	
	public List<AppointmentTransferDao> findByAppointment(AppointmentDao appointment);
	
	public List<AppointmentTransferDao> findByFromEmployee(UserDao fromEmployee);
	
	public List<AppointmentTransferDao> findByToEmployee(UserDao toEmployee);
}
