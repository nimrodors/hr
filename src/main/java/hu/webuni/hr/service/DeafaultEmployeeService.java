package hu.webuni.hr.service;

import org.springframework.stereotype.Service;

import hu.webuni.hr.model.Employee;

@Service
public class DeafaultEmployeeService implements EmployeeService {

	@Override
	public int getPayRaisePercent(Employee employee) {
		return 5;
	}

}
