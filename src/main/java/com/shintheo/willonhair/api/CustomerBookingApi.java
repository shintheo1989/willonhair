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
import org.springframework.web.bind.annotation.RequestParam;

import com.shintheo.willonhair.entity.CustomerBookingDao;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "CustomerBookingDao")
@RequestMapping(path = "/api/v1/appointments", produces = MediaType.APPLICATION_JSON_VALUE)
public interface CustomerBookingApi {	
	@GetMapping(path = "/{id}/bookings")
	public ResponseEntity<List<CustomerBookingDao>> all(@PathVariable(name = "id") Long appointmentId);
	
	@PostMapping(path = "/{id}/bookings")
	public ResponseEntity<CustomerBookingDao> createBooking(
			@PathVariable(name = "id") Long appointmentId,
			@ModelAttribute CustomerBookingDao booking,
			@RequestParam(required = true) Long customerId) throws NotFoundException;
	
	@PutMapping(path = "/{id}/bookings/{bookingId}")
	public ResponseEntity<CustomerBookingDao> updateBooking(
			@PathVariable("id") Long appointmentId, 
			@PathVariable("bookingId") Long bookingId, 
			@ModelAttribute CustomerBookingDao booking) throws NotFoundException;
	
	@DeleteMapping(path = "/{id}/bookings/{bookingId}")
	public ResponseEntity<String> deleteBooking(
			@PathVariable("id") Long appointmentId, 
			@PathVariable("bookingId") Long bookingId) throws NotFoundException ;
}
