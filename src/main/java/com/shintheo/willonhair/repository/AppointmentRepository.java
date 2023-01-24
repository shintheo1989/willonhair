package com.shintheo.willonhair.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shintheo.willonhair.entity.AppointmentDao;

@Repository
public interface AppointmentRepository  extends CrudRepository<AppointmentDao, Long> {

}
