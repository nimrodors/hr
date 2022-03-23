package hu.webuni.hr.web;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hu.webuni.hr.model.Employee;

@Controller
public class EmployeeTLController {
	
	List<Employee> employees = new ArrayList<>();
	
	{
		employees.add(new Employee(1L, "Sarolt", "gangszta", 100, LocalDateTime.of(2021, Month.SEPTEMBER, 1, 12, 24, 00)));
		employees.add(new Employee(2L, "Csuti", "miracle", 250, LocalDateTime.of(1985, Month.DECEMBER, 13, 23, 8, 00)));
		
	}

	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/employees")
	public String getAllEmployees(Map<String, Object> model) {
		model.put("employees", employees);
		model.put("newEmployee", new Employee());
		return "employees";
	}
	
	@PostMapping("/employees")
	public String addEmployee(Employee employee) {
		employees.add(employee);
		return "redirect:employees";
	}
}
