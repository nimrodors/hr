package hu.webuni.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{
	
}
