package com.avacado.job.application.job;

import java.util.List;

public interface ServiceJob {
	List<Job> findAll();
	void createJob(Job job);
	Job getJobWithId(Long id);
	boolean deleteJobWithId(Long id);
	boolean updateJob(Long id, Job updatedJob);
}
