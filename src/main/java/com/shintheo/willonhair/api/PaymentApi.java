package com.shintheo.willonhair.api;

import java.util.List;


import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shintheo.willonhair.entity.PaymentDao;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "PaymentDao")
@RequestMapping(path = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public interface PaymentApi {
	
	@GetMapping(path = "/payments")
	@Operation(summary = "All payments", description = "List all payments")
	public ResponseEntity<List<PaymentDao>> all();
	
	@GetMapping(path = "/customer-bookings/{id}/payments")
	@Operation(summary = "Booking's payments", description = "List payments on a given booking")
	public ResponseEntity<List<PaymentDao>> fetchBookingPayments(@PathVariable(name = "id") Long bookingId) throws NotFoundException;
	
	@PostMapping(path = "/payments")
	@Operation(summary = "Create payment", description = "Create a new payment")
	public ResponseEntity<PaymentDao> createPayment(@ModelAttribute PaymentDao payment) throws NotFoundException;
	
	@PostMapping(path = "/customer-bookings/{id}/payments")
	@Operation(summary = "Create booking's payment", description = "Create a new payment for a given booking")
	public ResponseEntity<PaymentDao> createBookingPayment(
			@PathVariable(name = "id") Long bookingId,
			@ModelAttribute PaymentDao payment) throws NotFoundException;
	
	@PutMapping(path = "/payments/{id}")
	@Operation(summary = "Update payment", description = "Update a given payment")
	public ResponseEntity<PaymentDao> updatePayment(
			@PathVariable("id") Long paymentId, 
			@ModelAttribute PaymentDao payment) throws NotFoundException;
	
	@DeleteMapping(path = "/payments/{id}")
	public ResponseEntity<String> deletePayment(@PathVariable("id") Long paymentId) throws NotFoundException;
}
