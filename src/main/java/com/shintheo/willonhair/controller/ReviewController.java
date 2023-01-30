package com.shintheo.willonhair.controller;

import java.sql.Timestamp;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.shintheo.willonhair.api.ReviewApi;
import com.shintheo.willonhair.entity.AppointmentDao;
import com.shintheo.willonhair.entity.ReviewDao;
import com.shintheo.willonhair.entity.UserDao;
import com.shintheo.willonhair.service.AppointmentService;
import com.shintheo.willonhair.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class ReviewController implements ReviewApi {

	@Autowired
	private ReviewService service;
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Override
	public ResponseEntity<List<ReviewDao>> all() {
		return ResponseEntity.ok(service.fetchAll());
	}
	
	@Override
	public ResponseEntity<List<ReviewDao>> appointmentReviews(@PathVariable(name = "id") Long appointmentId) throws NotFoundException {
		AppointmentDao appointment = appointmentService.findById(appointmentId).orElseThrow(() -> new NotFoundException());
		return ResponseEntity.ok(service.getAppointmentReviews(appointment));
	}
	
	@Override
	public ResponseEntity<ReviewDao> createReview(
			@PathVariable(name = "id") Long appointmentId,
			@ModelAttribute ReviewDao review,
			@AuthenticationPrincipal UserDao user) throws NotFoundException {
		AppointmentDao appointment = appointmentService.findById(appointmentId).orElseThrow(() -> new NotFoundException());
		review.setUser(user);
		review.setAppointment(appointment);
		review.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		return ResponseEntity.ok(service.submitReview(review));
	}
	
	@Override
	public ResponseEntity<ReviewDao> updateReview(
			@PathVariable("reviewId") Long reviewId, 
			@ModelAttribute ReviewDao review) throws NotFoundException {
		// TODO("This is not working")
		// Keep user and appointment
		// ReviewDao dbReview = service.findById(reviewId).orElseThrow();
		// dbReview.setComment(review.getComment());
		// dbReview.setMark(review.getMark());
		// review.setUser(dbReview.getUser());
		// review.setAppointment(dbReview.getAppointment());
		// review.setCreatedAt(dbReview.getCreatedAt());
		// review.setId(dbReview.getId());
		//return ResponseEntity.ok(service.saveReview(dbReview));
		
		return ResponseEntity.ok(null);
	}
	
	@Override
	public ResponseEntity<String> deleteReview(
			@PathVariable("reviewId") Long ReviewId) throws NotFoundException  {
		service.deleteReviewById(ReviewId);
		return ResponseEntity.ok("Review Deleted");
	}
}
