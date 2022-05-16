package hu.webuni.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.hr.model.AverageSalaryByPosition;
import hu.webuni.hr.model.Company;
import hu.webuni.hr.model.Employee;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	@Query("select distinct c from Company c, Employee e where c.companyNumber = e.company and e.salary > ?1")
	List<Company> getCompanyWithEmployeeSalary(int salary);

	@Query("select c from Company c where size(c.employees) > ?1")
	List<Company> getCompaniesWithEmployeeLimits(int number);

	@Query("SELECT e.post AS position, avg(e.salary) AS averageSalary "
			+ "FROM Company c "
			+ "INNER JOIN c.employees e "
			+ "WHERE c.companyNumber=:c " 
			+ "GROUP BY e.post "
			+ "ORDER BY avg(e.salary) DESC")
	public List<AverageSalaryByPosition> findAverageSalariesByPosition(long c);
	
	@Query("SELECT DISTINCT c FROM Company c LEFT JOIN FETCH c.employees")
	public List<Company> findAllWithEmployees();
}
