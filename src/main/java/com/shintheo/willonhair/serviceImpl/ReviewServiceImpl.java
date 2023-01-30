package com.shintheo.willonhair.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shintheo.willonhair.entity.AppointmentDao;
import com.shintheo.willonhair.entity.ReviewDao;
import com.shintheo.willonhair.repository.ReviewRepository;
import com.shintheo.willonhair.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewRepository repo;

	@Override
	public ReviewDao submitReview(ReviewDao review) {
		return repo.save(review);
	}

	@Override
	public ReviewDao saveReview(ReviewDao review) {
		return repo.save(review);
	}

	@Override
	public List<ReviewDao> fetchAll() {
		return repo.findAll();
	}

	@Override
	public List<ReviewDao> getAppointmentReviews(AppointmentDao appointment) {
		return repo.findByAppointment(appointment);
	}

	@Override
	public Optional<ReviewDao> findById(Long reviewId) {
		return repo.findById(reviewId);
	}

	@Override
	public ReviewDao updateReview(ReviewDao review, Long reviewId) {
		return repo.save(review);
	}

	@Override
	public void deleteReviewById(Long reviewId) {
		repo.deleteById(reviewId);
	}

}
