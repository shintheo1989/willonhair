package com.shintheo.willonhair.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shintheo.willonhair.entity.CustomerBookingDao;
import com.shintheo.willonhair.entity.PaymentDao;

public interface PaymentRepository  extends JpaRepository<PaymentDao, Long> {
	List<PaymentDao> findByCustomerBooking(CustomerBookingDao customerBooking);
}
