package com.avacado.applicationjob.review.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.avacado.applicationjob.company.Company;
import com.avacado.applicationjob.company.ServiceCompany;
import com.avacado.applicationjob.review.RepositoryReview;
import com.avacado.applicationjob.review.Review;
import com.avacado.applicationjob.review.ServiceReview;

@Service
public class ServiceReviewImpl implements ServiceReview {
	private RepositoryReview repositoryReview;
	private final ServiceCompany serviceCompany;
	
	
	public ServiceReviewImpl(RepositoryReview repositoryReview, ServiceCompany serviceCompany) {
		super();
		this.repositoryReview = repositoryReview;
		this.serviceCompany = serviceCompany;
	}


	@Override
	public List<Review> getAllReviews(Long companyId) {
		List<Review> reviews = repositoryReview.findByCompanyId(companyId);
		return reviews;
	}


	@Override
	public boolean addReview(Long companyId, Review review) {
		Company company = serviceCompany.getCompanyWithId(companyId);
		if(company != null) {
			review.setCompany(company);
			repositoryReview.save(review);
			return true;
		} else {
			return false;
		}
	}


	@Override
	public Review getReview(Long companyId, Long reviewId) {
		List<Review> reviews = repositoryReview.findByCompanyId(companyId);
		return reviews.stream().filter(review -> review.getId().equals(reviewId)).findFirst().orElse(null);
	}


	@Override
	public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
		if(serviceCompany.getCompanyWithId(companyId) != null) {
			updatedReview.setCompany(serviceCompany.getCompanyWithId(companyId));
			updatedReview.setId(reviewId);
			repositoryReview.save(updatedReview);
			return true;
		} else {
			return false;
		}
		
	}


	@Override
	public boolean deleteReview(Long companyId, Long reviewId) {
		if(serviceCompany.getCompanyWithId(companyId) != null && repositoryReview.existsById(reviewId)) {
			Review review = repositoryReview.findById(reviewId).orElse(null);
			Company company = review.getCompany();
			company.getReviews().remove(review);
			review.setCompany(null);
			serviceCompany.updateCompany(company, companyId);
			repositoryReview.deleteById(reviewId);
			return true;
		} else {
			return false;
		}
	}

}
