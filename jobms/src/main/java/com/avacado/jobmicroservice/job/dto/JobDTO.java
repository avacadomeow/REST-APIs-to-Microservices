package com.avacado.jobmicroservice.job.dto;

import java.util.List;

import com.avacado.jobmicroservice.job.external.Company;
import com.avacado.jobmicroservice.job.external.Review;

public class JobDTO {
	private Long id;
	private String role;
	private String description;
	private Long expectedSalary;
	private String location;
	private Company company;
	private List<Review> reviews;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getExpectedSalary() {
		return expectedSalary;
	}
	public void setExpectedSalary(Long expectedSalary) {
		this.expectedSalary = expectedSalary;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	

	
}
