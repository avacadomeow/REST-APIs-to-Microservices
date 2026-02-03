package com.avacado.applicationjob.review;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies/{companyId}")
public class ControllerReview {
	private ServiceReview serviceReview;

	public ControllerReview(ServiceReview serviceReview) {
		super();
		this.serviceReview = serviceReview;
	}
	
	@GetMapping("/reviews")
	public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
		return new ResponseEntity<>(serviceReview.getAllReviews(companyId), HttpStatus.OK);
	}
	
	@PostMapping("/reviews")
	public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review) {
		boolean isReviewSaved = serviceReview.addReview(companyId, review);
		if(isReviewSaved) {
			return new ResponseEntity<>("Review added successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Review not saved", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/reviews/{reviewId}")
	public ResponseEntity<Review> getReview(@PathVariable Long companyId, @PathVariable Long reviewId){
		return new ResponseEntity<>(serviceReview.getReview(companyId, reviewId), HttpStatus.OK);
	}
	
	@PutMapping("/reviews/{reviewId}")
	public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId,
			                                                                 @RequestBody Review review){
		boolean isReviewUpdated = serviceReview.updateReview(companyId, reviewId, review);
		if(isReviewUpdated) {
			return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Review not updated", HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/reviews/{reviewId}")
	public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId){
		boolean isReviewDeleted = serviceReview.deleteReview(companyId, reviewId);
		if(isReviewDeleted) {
			return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Review not deleted", HttpStatus.NOT_FOUND);
		}
	}

}
