package hu.webuni.hr.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import hu.webuni.hr.config.HrConfigurationProperties;
import hu.webuni.hr.model.Employee;

@Service
public class SmartEmployeeService extends AbstractEmployeeService {
	
	@Autowired
	HrConfigurationProperties hrConfigurationProperties;

	@Override
	public int getPayRaisePercent(Employee employee) {
		int month;

		month = employee.getMonth();
		System.out.println("Hónap: " + month);

		if (month >= 120) {
			return hrConfigurationProperties.getSalary().getSpecial().getMoreThanTen();
		} else if (month >= 60 && month < 120) {
			return hrConfigurationProperties.getSalary().getSpecial().getLessThanTenMoreThanFiveYears();
		} else if (month >= 30 && month < 60) {
			return hrConfigurationProperties.getSalary().getSpecial().getLessThanFiveMoreThanTwoAnHalfYears();
		} else {
			return hrConfigurationProperties.getSalary().getSpecial().getLessThanTwoAnHalfYears();
		}
	}

	
}
