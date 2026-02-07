package com.avacado.companymicroservice.company;

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
@RequestMapping("/companies")
public class ControllerCompany {
	private ServiceCompany serviceCompany;

	public ControllerCompany(ServiceCompany serviceCompany) {
		super();
		this.serviceCompany = serviceCompany;
	}
	
	
	@GetMapping
	public ResponseEntity<List<Company>> getAllComapnies() {
		return new ResponseEntity<>(serviceCompany.getAllCompanies(), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company) {
		serviceCompany.updateCompany(company, id);
		return new ResponseEntity<>("Company updated successfully", HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> addCompany(@RequestBody Company company) {
		serviceCompany.createCompany(company);
		return new ResponseEntity<>("Company added successfully", HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCompanyWithId(@PathVariable Long id) {
		boolean isDeleted = serviceCompany.deleteCompanyWithId(id);
		if(isDeleted) {
			return new ResponseEntity<>("Company deleted successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Company> getCompanyWithId(@PathVariable Long id) {
		Company company = serviceCompany.getCompanyWithId(id);
		if(company != null) {
			return new ResponseEntity<>(company, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	

}
