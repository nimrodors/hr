package hu.webuni.hr.service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.hr.model.Employee;
import hu.webuni.hr.repository.EmployeeRepository;

@Service
public abstract class AbstractEmployeeService implements EmployeeService {
	
//	private Map<Long, Employee> employees = new HashMap<>();
//	
//	{
//		employees.put(1L, new Employee(1L, "Sarolt", "gangszta", 100, LocalDateTime.of(2021, Month.SEPTEMBER, 1, 12, 24, 00)));
//		employees.put(2L, new Employee(2L, "Csuti", "miracle", 250, LocalDateTime.of(1985, Month.DECEMBER, 13, 23, 8, 00)));
//	}
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> getAll() {
		//return new ArrayList<>(employees.values());
		return employeeRepository.findAll();
	}
	
	@Override
	public Employee getById(long id) {
		// TODO Auto-generated method stub
		//return employees.get(id);
		return employeeRepository.getById(id);
	}
	
	@Override
	@Transactional
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	@Transactional
	public Employee modifyEmployee(long id, Employee employee) {
//		employee.setId(id);
//		employees.put(employee.getId(), employee);
//		return employee;
		if(employeeRepository.existsById(employee.getId()))
			return employeeRepository.save(employee);
		else 
			throw new NoSuchElementException();
	}

	@Override
	@Transactional
	public void deleteEmployee(long id) {
		//employees.remove(id);
		employeeRepository.deleteById(id);
	}

	@Override
	public List<Employee> getEmployeesWithBiggerSalary(int salary) {
		
//		return new ArrayList<>(employees.values()
//				.stream()
//				.filter(s -> salary < s.getSalary())
//				.collect(Collectors.toList()));
		return employeeRepository.findBySalaryGreaterThan(salary);
	}
	
	@Override
	public List<Employee> getEmployeeWithPost(String post) {
		return employeeRepository.findByPostEquals(post);
	}

	@Override
	public List<Employee> getEmployeeWithCertainLetter(String letter) {
		return employeeRepository.findByNameStartingWithIgnoreCase(letter);
	}

	@Override
	public List<Employee> getEmployeesBetweenDate(LocalDateTime from, LocalDateTime to) {
		return employeeRepository.findByLocalDateTimeBetween(from, to);
	}
//
//	public Map<Long, Employee> getEmployees() {
//		return employees;
//	}
//
//	public void setEmployees(Map<Long, Employee> employees) {
//		this.employees = employees;
//	}
	
}
