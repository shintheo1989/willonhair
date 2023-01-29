package com.shintheo.willonhair.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shintheo.willonhair.entity.CustomerBookingDao;

public interface CustomerBookingRepository extends JpaRepository<CustomerBookingDao, Long> {

}
