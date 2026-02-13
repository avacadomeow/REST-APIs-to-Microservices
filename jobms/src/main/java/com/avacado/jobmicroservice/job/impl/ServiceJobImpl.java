package com.avacado.jobmicroservice.job.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.avacado.jobmicroservice.job.external.Company;
import com.avacado.jobmicroservice.job.external.Review;
import com.avacado.jobmicroservice.job.mapper.MapperJob;
import com.avacado.jobmicroservice.job.Job;
import com.avacado.jobmicroservice.job.RepositoryJob;
import com.avacado.jobmicroservice.job.ServiceJob;
import com.avacado.jobmicroservice.job.dto.JobDTO;

@Service
public class ServiceJobImpl implements ServiceJob{
	//private List<Job> jobs = new ArrayList<>();
	RepositoryJob repositoryJob;
	
	@Autowired
	RestTemplate restTemplate;
	
	public ServiceJobImpl(RepositoryJob repositoryJob) {
		super();
		this.repositoryJob = repositoryJob;
	}

	@Override
	public List<JobDTO> findAll() {
		List<Job> jobs = repositoryJob.findAll();
		List<JobDTO> jobDTOs = new ArrayList<>(); 
		return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
	}
	
	private JobDTO convertToDto(Job job) {
		//RestTemplate restTemplate = new RestTemplate();
		Company company = restTemplate.getForObject("http://companyms:8081/companies/" + job.getCompanyId(), Company.class);
		
		ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange("http://reviewms:8083/reviews?companyId=" + job.getCompanyId(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() {
		});
		
		List<Review> reviews = reviewResponse.getBody();
		
		JobDTO jobDTO = MapperJob.mapToJobCompanyDTO(job, company, reviews);
		//jobDTO.setCompany(company);
			
		return jobDTO;
	}

	@Override
	public void createJob(Job job) {
		repositoryJob.save(job);
		
	}

	@Override
	public JobDTO getJobWithId(Long id) {
		Job job = repositoryJob.findById(id).orElse(null);
		return convertToDto(job);
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

