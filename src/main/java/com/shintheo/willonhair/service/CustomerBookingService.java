package com.shintheo.willonhair.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shintheo.willonhair.entity.CustomerBookingDao;

public interface CustomerBookingService {
	
	// Save operation
	public CustomerBookingDao saveBooking(CustomerBookingDao booking);
		
	// Read operation
	public List<CustomerBookingDao> fetchBookingList() ;
	
	public Optional<CustomerBookingDao> findById(Long bookingId);
		
	// Update operation
	public CustomerBookingDao updateBooking(CustomerBookingDao booking, Long bookingId);
		
	// Delete operation
	public void deleteBookingById(Long bookingId);
}
