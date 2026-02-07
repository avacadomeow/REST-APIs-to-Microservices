package com.avacado.jobmicroservice.job;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryJob extends JpaRepository<Job, Long> {

}
