package com.shintheo.willonhair.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shintheo.willonhair.entity.CustomerBookingDao;
import com.shintheo.willonhair.repository.CustomerBookingRepository;
import com.shintheo.willonhair.service.CustomerBookingService;

@Service
public class CustomerBookingServiceImpl implements CustomerBookingService{
	
	@Autowired
	private CustomerBookingRepository repository;

	@Override
	public CustomerBookingDao saveBooking(CustomerBookingDao booking) {
		return repository.save(booking);
	}

	@Override
	public List<CustomerBookingDao> fetchBookingList() {
		return repository.findAll();
	}

	@Override
	public Optional<CustomerBookingDao> findById(Long bookingId) {
		return repository.findById(bookingId);
	}

	@Override
	public CustomerBookingDao updateBooking(CustomerBookingDao booking, Long bookingId) {
		booking.setId(bookingId);
		return repository.save(booking);
	}

	@Override
	public void deleteBookingById(Long bookingId) {
		repository.deleteById(bookingId);
	}

}
