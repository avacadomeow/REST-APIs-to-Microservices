package com.avacado.jobmicroservice.job.mapper;

import java.util.List;

import com.avacado.jobmicroservice.job.Job;
import com.avacado.jobmicroservice.job.dto.JobDTO;
import com.avacado.jobmicroservice.job.external.Company;
import com.avacado.jobmicroservice.job.external.Review;

public class MapperJob {
	public static JobDTO mapToJobCompanyDTO(Job job, Company company, List<Review> reviews) {
		
		JobDTO jobDTO = new JobDTO();
		jobDTO.setId(job.getId());
		jobDTO.setRole(job.getRole());
		jobDTO.setDescription(job.getDescription());
		jobDTO.setLocation(job.getLocation());
		jobDTO.setExpectedSalary(job.getExpectedSalary());
		jobDTO.setCompany(company);
		jobDTO.setReviews(reviews);
		
		return jobDTO;
	}
}
