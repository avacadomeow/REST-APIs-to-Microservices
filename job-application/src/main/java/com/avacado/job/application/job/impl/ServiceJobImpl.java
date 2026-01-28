package com.avacado.job.application.job.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.avacado.job.application.job.Job;
import com.avacado.job.application.job.ServiceJob;

@Service
public class ServiceJobImpl implements ServiceJob{
	private List<Job> jobs = new ArrayList<>();
	private Long nextId = 1L;
	
	@Override
	public List<Job> findAll() {
		return jobs;
	}

	@Override
	public void createJob(Job job) {
		job.setId(nextId++);
		jobs.add(job);
		
	}

	@Override
	public Job getJobWithId(Long id) {
		for(Job job : jobs) {
			if (job.getId().equals(id)) {
				return job;
			}
		}
		return null;
	}

	@Override
	public boolean deleteJobWithId(Long id) {
		Iterator<Job> iterator = jobs.iterator();
		while (iterator.hasNext()) {
			Job job = iterator.next();
			if (job.getId().equals(id)) {
				iterator.remove();
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean updateJob(Long id, Job updatedJob) {
		for (Job job : jobs) {
			if (job.getId().equals(id)) {
				job.setRole(updatedJob.getRole());
				job.setDescription(updatedJob.getDescription());
				job.setExpectedSalary(updatedJob.getExpectedSalary());
				job.setLocation(updatedJob.getLocation());
				return true;
			}
		}
		return false;
	}
	
}
