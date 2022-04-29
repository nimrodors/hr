package hu.webuni.hr.service;

import java.time.LocalDateTime;
import java.time.Month;

import org.hibernate.validator.internal.constraintvalidators.bv.notempty.NotEmptyValidatorForArraysOfLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.hr.model.Company;
import hu.webuni.hr.model.Employee;
import hu.webuni.hr.repository.CompanyRepository;
import hu.webuni.hr.repository.EmployeeRepository;

@Service
public class InitDbService {
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	public void deleteAllCompany() {
		companyRepository.deleteAll();
	}
	
	@Transactional
	public void inserTestData () {
		//Employee e1 = new Employee(null, "Nora", "DJ", 4897, LocalDateTime.of(1997, Month.APRIL, 5, 23, 07, 00));
		Employee e1 = employeeRepository.save(new Employee(null, "Nora", "DJ", 4897, LocalDateTime.of(1997, Month.APRIL, 5, 23, 07, 00)));
		Employee e2 = employeeRepository.save(new Employee(null, "Róka Rudi", "Játék", 781, LocalDateTime.of(2016, Month.JULY, 1, 11, 45, 00)));
		
		Company c1 = companyRepository.save(new Company("Hajóágyú", "MG. Street R.", null));
		c1.addEmployee(e1);
		c1.addEmployee(e2);
	}
	
}
