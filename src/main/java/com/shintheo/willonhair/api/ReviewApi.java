package com.shintheo.willonhair.api;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shintheo.willonhair.entity.ReviewDao;
import com.shintheo.willonhair.entity.UserDao;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "ReviewDao")
@RequestMapping(path = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public interface ReviewApi {
	
	@GetMapping(path = "/reviews")
	@Operation(summary = "List reviews", description = "List all riviews")
	public ResponseEntity<List<ReviewDao>> all();
	
	@GetMapping(path = "/appointments/{id}/reviews")
	@Operation(summary = "List appointment's reviews", description = "List riviews on a given appointment")
	public ResponseEntity<List<ReviewDao>> appointmentReviews(@PathVariable(name = "id") Long appointmentId) throws NotFoundException;
	
	@PostMapping(path = "/appointments/{id}/reviews")
	@Operation(summary = "New review", description = "Create a new review on a given appointment")
	public ResponseEntity<ReviewDao> createReview(
			@PathVariable(name = "id") Long appointmentId,
			@ModelAttribute ReviewDao Review,
			@AuthenticationPrincipal UserDao user
			) throws NotFoundException;
	
	@PutMapping(path = "/appointments/{id}/reviews/{reviewId}")
	@Operation(summary = "Update review", description = "Update a given review")
	public ResponseEntity<ReviewDao> updateReview(
			@PathVariable("reviewId") Long ReviewId, 
			@ModelAttribute ReviewDao Review) throws NotFoundException ;
	
	@DeleteMapping(path = "/appointments/{id}/reviews/{reviewId}")
	@Operation(summary = "Delete review", description = "Delete a given review")
	public ResponseEntity<String> deleteReview(
			@PathVariable("reviewId") Long ReviewId) throws NotFoundException ;
	
}
