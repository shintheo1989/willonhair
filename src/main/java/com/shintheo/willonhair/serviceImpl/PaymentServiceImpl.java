package com.shintheo.willonhair.serviceImpl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shintheo.willonhair.entity.CustomerBookingDao;
import com.shintheo.willonhair.entity.PaymentDao;
import com.shintheo.willonhair.repository.PaymentRepository;
import com.shintheo.willonhair.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository repo;

	@Override
	public PaymentDao submitPayment(PaymentDao payment) {
		return repo.save(payment);
	}

	@Override
	public PaymentDao savePayment(PaymentDao payment) {
		return repo.save(payment);
	}

	@Override
	public List<PaymentDao> fetchAll() {
		return repo.findAll();
	}

	@Override
	public List<PaymentDao> getBookingPayments(CustomerBookingDao booking) {
		return repo.findByCustomerBooking(booking);
	}

	@Override
	public Optional<PaymentDao> findById(Long paymentId) {
		return repo.findById(paymentId);
	}

	@Override
	public PaymentDao updatePayment(PaymentDao Payment, Long paymentId) {
		return repo.save(Payment);
	}

	@Override
	public void deletePaymentById(Long paymentId) {
		repo.deleteById(paymentId);
	}
}
