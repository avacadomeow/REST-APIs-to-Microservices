package com.avacado.applicationjob.company;

import java.util.List;

public interface ServiceCompany {
	List<Company> getAllCompanies();
	boolean updateCompany(Company company, Long id);
	void createCompany(Company company);
	boolean deleteCompanyWithId(Long id);
	Company getCompanyWithId(Long id);
}
