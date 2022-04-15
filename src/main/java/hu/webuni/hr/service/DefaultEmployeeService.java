package hu.webuni.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import hu.webuni.hr.config.HrConfigurationProperties;
import hu.webuni.hr.model.Employee;

@Service
public class DefaultEmployeeService extends AbstractEmployeeService {
	
	@Autowired
	HrConfigurationProperties hrConfigurationProperties;

	@Override
	public int getPayRaisePercent(Employee employee) {
		return hrConfigurationProperties.getSalary().getDef().getPercent();
	}
}
