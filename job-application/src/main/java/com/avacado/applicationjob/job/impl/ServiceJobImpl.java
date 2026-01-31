package com.avacado.applicationjob.job.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.avacado.applicationjob.job.Job;
import com.avacado.applicationjob.job.RepositoryJob;
import com.avacado.applicationjob.job.ServiceJob;

@Service
public class ServiceJobImpl implements ServiceJob{
	//private List<Job> jobs = new ArrayList<>();
	RepositoryJob repositoryJob;
	
	public ServiceJobImpl(RepositoryJob repositoryJob) {
		super();
		this.repositoryJob = repositoryJob;
	}

	@Override
	public List<Job> findAll() {
		return repositoryJob.findAll();
	}

	@Override
	public void createJob(Job job) {
		repositoryJob.save(job);
		
	}

	@Override
	public Job getJobWithId(Long id) {
		return repositoryJob.findById(id).orElse(null);
	}

	@Override
	public boolean deleteJobWithId(Long id) {
		try {
			repositoryJob.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
		
	@Override
	public boolean updateJob(Long id, Job updatedJob) {
		Optional<Job> jobOptional = repositoryJob.findById(id);
			if (jobOptional.isPresent()) {
				Job job = jobOptional.get();
				job.setRole(updatedJob.getRole());
				job.setDescription(updatedJob.getDescription());
				job.setExpectedSalary(updatedJob.getExpectedSalary());
				job.setLocation(updatedJob.getLocation());
				repositoryJob.save(job);
				return true;
			}
		return false;
	}
}

