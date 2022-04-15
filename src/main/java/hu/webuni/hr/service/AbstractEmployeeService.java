package hu.webuni.hr.service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import hu.webuni.hr.model.Employee;

@Service
public abstract class AbstractEmployeeService implements EmployeeService {
	
	private Map<Long, Employee> employees = new HashMap<>();
	
	{
		employees.put(1L, new Employee(1L, "Sarolt", "gangszta", 100, LocalDateTime.of(2021, Month.SEPTEMBER, 1, 12, 24, 00)));
		employees.put(2L, new Employee(2L, "Csuti", "miracle", 250, LocalDateTime.of(1985, Month.DECEMBER, 13, 23, 8, 00)));
	}
	
	@Override
	public List<Employee> getAll() {
		return new ArrayList<>(employees.values());
	}
	
	@Override
	public Employee getById(long id) {
		// TODO Auto-generated method stub
		return employees.get(id);
	}
	
	@Override
	public Employee createEmployee(Employee employee) {
		employees.put(employee.getId(), employee);
		return employee;
	}

	@Override
	public Employee modifyEmployee(long id, Employee employee) {
		employee.setId(id);
		employees.put(employee.getId(), employee);
		return employee;
	}

	@Override
	public void deleteEmployee(long id) {
		employees.remove(id);
	}

	@Override
	public List<Employee> getEmployeesWithBiggerSalary(int salary) {
		
		return new ArrayList<>(employees.values()
				.stream()
				.filter(s -> salary < s.getSalary())
				.collect(Collectors.toList()));
	}
}
