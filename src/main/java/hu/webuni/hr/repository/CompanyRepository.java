package hu.webuni.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.hr.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{
	
	@Query("select c from Company c, Employee e where c.companyNumber = e.company and e.salary > ?1")
	List<Company> getCompanyWithEmployeeSalary(int salary);
	
	@Query("select c from Company c where size(c.employees) > ?1")
	List<Company> getCompaniesWithEmployeeLimits(int number);
}
