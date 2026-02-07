package com.avacado.reviewmicroservice.review.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.avacado.reviewmicroservice.review.RepositoryReview;
import com.avacado.reviewmicroservice.review.Review;
import com.avacado.reviewmicroservice.review.ServiceReview;

@Service
public class ServiceReviewImpl implements ServiceReview {
	private RepositoryReview repositoryReview;
	
	
	public ServiceReviewImpl(RepositoryReview repositoryReview) {
		super();
		this.repositoryReview = repositoryReview;
	}


	@Override
	public List<Review> getAllReviews(Long companyId) {
		List<Review> reviews = repositoryReview.findByCompanyId(companyId);
		return reviews;
	}


	@Override
	public boolean addReview(Long companyId, Review review) {
		if(companyId != null && review != null) {
			review.setCompanyId(companyId);
			repositoryReview.save(review);
			return true;
		} else {
			return false;
		}
	}


	@Override
	public Review getReview(Long reviewId) {
		return repositoryReview.findById(reviewId).orElse(null);
	}


	@Override
	public boolean updateReview(Long reviewId, Review updatedReview) {
		Review review = repositoryReview.findById(reviewId).orElse(null);
		if(review != null) {
			review.setTitle(updatedReview.getTitle());
			review.setDescription(updatedReview.getDescription());
			review.setRating(updatedReview.getRating());
			review.setCompanyId(updatedReview.getCompanyId());
			repositoryReview.save(review);
			return true;
		} else {
			return false;
		}
		
	}


	@Override
	public boolean deleteReview(Long reviewId) {
		Review review = repositoryReview.findById(reviewId).orElse(null);
		if(review != null) {
			repositoryReview.delete(review);
			return true;
		} else {
			return false;
		}
	}

}
