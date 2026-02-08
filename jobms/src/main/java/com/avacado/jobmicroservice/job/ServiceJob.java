package com.avacado.jobmicroservice.job;

import java.util.List;

import com.avacado.jobmicroservice.job.dto.JobCompanyDTO;

public interface ServiceJob {
	List<JobCompanyDTO> findAll();
	void createJob(Job job);
	Job getJobWithId(Long id);
	boolean deleteJobWithId(Long id);
	boolean updateJob(Long id, Job updatedJob);
}
