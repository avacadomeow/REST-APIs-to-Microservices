package com.avacado.job.application.job;

public class Job {
	private Long id;
	private String role;
	private String description;
	private Long expectedSalary;
	private String location;
	
	public Job(Long id, String role, String description, Long expectedSalary, String location) {
		super();
		this.id = id;
		this.role = role;
		this.description = description;
		this.expectedSalary = expectedSalary;
		this.location = location;
	}

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
	
	
}
