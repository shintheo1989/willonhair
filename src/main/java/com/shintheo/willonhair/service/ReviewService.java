package com.shintheo.willonhair.service;

import java.util.List;
import java.util.Optional;

import com.shintheo.willonhair.entity.AppointmentDao;
import com.shintheo.willonhair.entity.ReviewDao;

public interface ReviewService {
	
	ReviewDao submitReview(ReviewDao review);

	ReviewDao saveReview(ReviewDao review);
		
	List<ReviewDao> fetchAll();
	
	List<ReviewDao> getAppointmentReviews(AppointmentDao appointment);
	
	Optional<ReviewDao> findById(Long reviewId);
	
	ReviewDao updateReview(ReviewDao review, Long reviewId);
	
	void deleteReviewById(Long reviewId);
}