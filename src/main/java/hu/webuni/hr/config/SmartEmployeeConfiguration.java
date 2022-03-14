package hu.webuni.hr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.webuni.hr.service.EmployeeService;
import hu.webuni.hr.service.SmartEmployeeService;

@Configuration
@Profile("smart")
public class SmartEmployeeConfiguration {

	@Bean
	public EmployeeService employeeService() {
		return new SmartEmployeeService();
	}
}
