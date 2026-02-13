package com.avacado.jobmicroservice.job;

import java.util.List;

import com.avacado.jobmicroservice.job.dto.JobDTO;

public interface ServiceJob {
	List<JobDTO> findAll();
	void createJob(Job job);
	JobDTO getJobWithId(Long id);
	boolean deleteJobWithId(Long id);
	boolean updateJob(Long id, Job updatedJob);
}
