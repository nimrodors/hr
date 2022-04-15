package hu.webuni.hr.service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hu.webuni.hr.model.Employee;

public abstract class AbstractEmployeeService implements EmployeeService {
	
	private Map<Long, Employee> employees = new HashMap<>();
	
	{
		employees.put(1L, new Employee(1L, "Sarolt", "gangszta", 100, LocalDateTime.of(2021, Month.SEPTEMBER, 1, 12, 24, 00)));
		employees.put(2L, new Employee(2L, "Csuti", "miracle", 250, LocalDateTime.of(1985, Month.DECEMBER, 13, 23, 8, 00)));
	}
	
	@Override
	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Employee getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Employee createEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee modifyEmployee(long id, Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteEmployee(long id) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Employee> getEmployeesWithBiggerSalary(int salary) {
		// TODO Auto-generated method stub
		return null;
	}
}
