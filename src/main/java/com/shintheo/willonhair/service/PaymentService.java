package com.shintheo.willonhair.service;

import java.util.List;
import java.util.Optional;

import com.shintheo.willonhair.entity.CustomerBookingDao;
import com.shintheo.willonhair.entity.PaymentDao;
public interface PaymentService {
	PaymentDao submitPayment(PaymentDao payment);

	PaymentDao savePayment(PaymentDao payment);
		
	List<PaymentDao> fetchAll();

	List<PaymentDao> getBookingPayments(CustomerBookingDao booking);
	
	Optional<PaymentDao> findById(Long paymentId);
	
	PaymentDao updatePayment(PaymentDao payment, Long paymentId);
	
	void deletePaymentById(Long paymentId);
}