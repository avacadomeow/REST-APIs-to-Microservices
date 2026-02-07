package com.avacado.companymicroservice.company.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.avacado.companymicroservice.company.Company;
import com.avacado.companymicroservice.company.RepositoryCompany;
import com.avacado.companymicroservice.company.ServiceCompany;

@Service
public class ServiceCompnayImpl implements ServiceCompany {
	private RepositoryCompany repositoryCompany;
	
	
	public ServiceCompnayImpl(RepositoryCompany repositoryCompany) {
		super();
		this.repositoryCompany = repositoryCompany;
	}


	@Override
	public List<Company> getAllCompanies() {
		return repositoryCompany.findAll();
	}


	@Override
	public boolean updateCompany(Company company, Long id) {
		Optional<Company> companyOptional = repositoryCompany.findById(id);
		if (companyOptional.isPresent()) {
			Company companyToUpdate = companyOptional.get();
			companyToUpdate.setDescription(company.getDescription());
			companyToUpdate.setName(company.getName());
			repositoryCompany.save(companyToUpdate);
			return true;
		} else {
			return false;
		}
	}


	@Override
	public void createCompany(Company company) {
		repositoryCompany.save(company);
		
	}


	@Override
	public boolean deleteCompanyWithId(Long id) {
		if(repositoryCompany.existsById(id)) {
			repositoryCompany.deleteById(id);
			return true;
		} else {
			return false;
		}
	}


	@Override
	public Company getCompanyWithId(Long id) {
		return repositoryCompany.findById(id).orElse(null);
	}

}
