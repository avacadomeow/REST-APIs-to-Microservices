package com.avacado.jobmicroservice.job.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.avacado.jobmicroservice.job.external.Company;
import com.avacado.jobmicroservice.job.Job;
import com.avacado.jobmicroservice.job.RepositoryJob;
import com.avacado.jobmicroservice.job.ServiceJob;
import com.avacado.jobmicroservice.job.dto.JobCompanyDTO;

@Service
public class ServiceJobImpl implements ServiceJob{
	//private List<Job> jobs = new ArrayList<>();
	RepositoryJob repositoryJob;
	
	public ServiceJobImpl(RepositoryJob repositoryJob) {
		super();
		this.repositoryJob = repositoryJob;
	}

	@Override
	public List<JobCompanyDTO> findAll() {
		List<Job> jobs = repositoryJob.findAll();
		List<JobCompanyDTO> jobCompanyDTOs = new ArrayList<>(); 
		
		RestTemplate restTemplate = new RestTemplate();
		for(Job job : jobs) {
			JobCompanyDTO jobCompanyDTO = new JobCompanyDTO();
			jobCompanyDTO.setJob(job);
			Company company = restTemplate.getForObject("http://localhost:8081/companies/" + job.getCompanyId(), Company.class);
			jobCompanyDTO.setCompany(company);
			jobCompanyDTOs.add(jobCompanyDTO);
		}
		return jobCompanyDTOs;
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

