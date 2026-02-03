package com.avacado.applicationjob.review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryReview extends JpaRepository<Review, Long> {

	List<Review> findByCompanyId(Long companyId);

}
