package com.shintheo.willonhair.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shintheo.willonhair.entity.AppointmentDao;
import com.shintheo.willonhair.entity.UserDao;

public interface AppointmentRepository  extends JpaRepository<AppointmentDao, Long> {
	List<AppointmentDao> findByEmployee(UserDao employee);
}
