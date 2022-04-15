package hu.webuni.hr.service;


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
}
