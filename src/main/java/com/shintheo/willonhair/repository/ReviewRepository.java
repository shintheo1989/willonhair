package com.shintheo.willonhair.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shintheo.willonhair.entity.AppointmentDao;
import com.shintheo.willonhair.entity.ReviewDao;

public interface ReviewRepository  extends JpaRepository<ReviewDao, Long> {
	
	public List<ReviewDao> findByAppointment(AppointmentDao appointment);
}
