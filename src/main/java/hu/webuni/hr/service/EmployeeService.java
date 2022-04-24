package hu.webuni.hr.service;


import java.time.LocalDateTime;
import java.util.List;

import hu.webuni.hr.model.Employee;

public interface EmployeeService {
	
	public int getPayRaisePercent(Employee employee);
	
	public List<Employee> getAll();
	
	public Employee getById(long id);
	
	public Employee createEmployee(Employee employee);
	
	public Employee modifyEmployee(long id, Employee employee);
	
	public void deleteEmployee(long id);
	
	public List<Employee> getEmployeesWithBiggerSalary(int salary);
	
	public List<Employee> getEmployeeWithPost(String post);
	
	public List<Employee> getEmployeeWithCertainLetter(String letters);
	
	public List<Employee> getEmployeesBetweenDate(LocalDateTime before, LocalDateTime after);
}
