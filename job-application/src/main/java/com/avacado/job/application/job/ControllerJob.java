package com.avacado.job.application.job;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class ControllerJob {
	private ServiceJob serviceJob;
	
	
	public ControllerJob(ServiceJob serviceJob) {
		this.serviceJob = serviceJob;
	}

	@GetMapping
	public ResponseEntity<List<Job>> findAll(){
		return ResponseEntity.ok(serviceJob.findAll());
	}
	
	@PostMapping
	public ResponseEntity<String> createJob(@RequestBody Job job) {
		serviceJob.createJob(job);
		return new ResponseEntity<>("New job added", HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Job> getJobWithId(@PathVariable Long id) {
		Job job = serviceJob.getJobWithId(id);
		if(job != null)
			return new ResponseEntity<>(job, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteJob(@PathVariable Long id) {
		boolean delete = serviceJob.deleteJobWithId(id);
		if (delete)
			return new ResponseEntity<>("Job delete success", HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
		boolean update = serviceJob.updateJob(id, updatedJob);
		if (update)
			return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
