package com.shintheo.willonhair.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.shintheo.willonhair.api.PaymentApi;
import com.shintheo.willonhair.entity.CustomerBookingDao;
import com.shintheo.willonhair.entity.PaymentDao;
import com.shintheo.willonhair.service.CustomerBookingService;
import com.shintheo.willonhair.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class PaymentController implements PaymentApi {

	@Autowired
	private PaymentService service;
	
	@Autowired
	private CustomerBookingService bookingService;

	@Override
	public ResponseEntity<List<PaymentDao>> all() {
		return ResponseEntity.ok(service.fetchAll());
	}

	@Override
	public ResponseEntity<List<PaymentDao>> fetchBookingPayments(@PathVariable(name = "id") Long bookingId) throws NotFoundException {
		CustomerBookingDao Booking = bookingService.findById(bookingId).orElseThrow();
		return ResponseEntity.ok(service.getBookingPayments(Booking));
	}

	@Override
	public ResponseEntity<PaymentDao> createPayment(@ModelAttribute PaymentDao payment) throws NotFoundException {
		return ResponseEntity.ok(service.submitPayment(payment));
	}

	@Override
	public ResponseEntity<PaymentDao> createBookingPayment(
			@PathVariable(name = "id") Long bookingId,
			@ModelAttribute PaymentDao payment) throws NotFoundException {
		CustomerBookingDao Booking = bookingService.findById(bookingId).orElseThrow();
		payment.setCustomerBooking(Booking);
		return ResponseEntity.ok(service.submitPayment(payment));
	}

	@Override
	public ResponseEntity<PaymentDao> updatePayment(
			@PathVariable("id") Long paymentId, 
			@ModelAttribute PaymentDao payment) throws NotFoundException {
		PaymentDao dbPayment = service.findById(paymentId).orElseThrow();
		payment.setCustomerBooking(dbPayment.getCustomerBooking());
		payment.setParent(dbPayment.getParent());
		payment.setId(paymentId);
		return ResponseEntity.ok(service.savePayment(payment));
	}

	@Override
	public ResponseEntity<String> deletePayment(@PathVariable("id") Long paymentId) throws NotFoundException {
		service.deletePaymentById(paymentId);
		return ResponseEntity.ok("PaymentDeleted");
	}
}
