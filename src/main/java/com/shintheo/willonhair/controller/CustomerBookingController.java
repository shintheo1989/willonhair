package com.shintheo.willonhair.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shintheo.willonhair.api.CustomerBookingApi;
import com.shintheo.willonhair.entity.AppointmentDao;
import com.shintheo.willonhair.entity.CustomerBookingDao;
import com.shintheo.willonhair.entity.UserDao;
import com.shintheo.willonhair.service.AppointmentService;
import com.shintheo.willonhair.service.CustomerBookingService;
import com.shintheo.willonhair.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
public class CustomerBookingController implements CustomerBookingApi {

	@Autowired
	private AppointmentService appointmentService;	

	@Autowired
	private UserService userService;	
	
	@Autowired
	private CustomerBookingService bookingService;
	
	@Override
	public ResponseEntity<List<CustomerBookingDao>> all(@PathVariable(name = "id") Long appointmentId) {
		log.info("Return list of booking for appointment");
		return ResponseEntity.ok(appointmentService.findById(appointmentId).orElseThrow().getBookinks());
	}

	@Override
	public ResponseEntity<CustomerBookingDao> createBooking(
			@PathVariable(name = "id") Long appointmentId,
			@ModelAttribute CustomerBookingDao booking,
			@RequestParam(required = true) Long customerId) throws NotFoundException {
		AppointmentDao appointment = appointmentService.findById(appointmentId).orElseThrow();
		UserDao user = userService.getUserById(customerId);
		booking.setAppointment(appointment);
		booking.setCustomer(user);
		return ResponseEntity.ok(bookingService.saveBooking(booking));
	}

	@Override
	public ResponseEntity<CustomerBookingDao> updateBooking(
			@PathVariable("id") Long appointmentId, 
			@PathVariable("bookingId") Long bookingId, 
			@ModelAttribute CustomerBookingDao booking) throws NotFoundException {
		CustomerBookingDao dbBooking = bookingService.findById(bookingId).orElseThrow();
		// Make sure we preserve appointment and customer
		booking.setCustomer(dbBooking.getCustomer());
		booking.setAppointment(dbBooking.getAppointment());
		return ResponseEntity.ok(bookingService.updateBooking(booking, bookingId));	}

	@Override
	public ResponseEntity<String> deleteBooking(
			@PathVariable("id") Long appointmentId, 
			@PathVariable("bookingId") Long bookingId) throws NotFoundException {
		bookingService.deleteBookingById(bookingId);
		return ResponseEntity.ok("Booking deleted");
	}	
}
