package hu.webuni.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.model.Employee;

@Service
public class SalaryService {

	@Autowired
	EmployeeService employeeService;

	public Employee newSalary(Employee employee) {
		int salary;
		salary = (int) (employee.getSalary() * (employeeService.getPayRaisePercent(employee) / 100.0)
				+ employee.getSalary());
		
		employee.setSalary(salary);
		
		return employee;

	}
}
