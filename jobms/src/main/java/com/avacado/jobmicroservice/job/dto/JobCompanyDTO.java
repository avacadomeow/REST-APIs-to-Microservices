package com.avacado.jobmicroservice.job.dto;

import com.avacado.jobmicroservice.job.Job;
import com.avacado.jobmicroservice.job.external.Company;

public class JobCompanyDTO {
	private Job job;
	private Company company;
	
	
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}

	
}
