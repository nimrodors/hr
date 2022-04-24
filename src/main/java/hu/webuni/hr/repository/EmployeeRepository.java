package hu.webuni.hr.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.hr.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	@Query("select e from Employee e where e.salary > ?1")
	List<Employee> findBySalaryGreaterThan (int salary);
	
	List<Employee> findByPostEquals (String post);
	
	List<Employee> findByNameStartingWithIgnoreCase (String letter);
	
	List<Employee> findByLocalDateTimeBetween (LocalDateTime from, LocalDateTime to);
}
