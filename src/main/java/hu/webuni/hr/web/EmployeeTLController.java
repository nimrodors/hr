package hu.webuni.hr.web;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import hu.webuni.hr.model.Employee;

@Controller
public class EmployeeTLController {

	List<Employee> employees = new ArrayList<>();

	{
		employees.add(
				new Employee(1L, "Sarolt", "gangszta", 100, LocalDateTime.of(2021, Month.SEPTEMBER, 1, 12, 24, 00)));
		employees.add(new Employee(2L, "Csuti", "miracle", 250, LocalDateTime.of(1985, Month.DECEMBER, 13, 23, 8, 00)));

	}

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/employees")
	public String getAllEmployees(Map<String, Object> model) {
		System.out.println("Kilistáz");
		model.put("employees", employees);
		model.put("newEmployee", new Employee());
		return "employees";
	}

	// törlöm az alkalmazottat
	@RequestMapping("/deleteEmployee{id}")
	public String deleteEmployees(@RequestParam Long id) {
		System.out.println(id);
		employees.removeIf(e -> e.getId() == id);
		//employees.forEach(e -> e.getName());
//			employees.remove(id);
//			System.out.println("Törlésig eljutok");
		return "redirect:/employees";
	}

	@PostMapping("/employees")
	public String addEmployee(Employee employee) {
		employees.add(employee);
		return "redirect:employees";
	}

	// Kilistázza az alkalmazott adatait az updateemployee.html -en
	@GetMapping("/employees/{id}")
	public String updateEmployee(@PathVariable Long id, Map<String, Object> model) {
		System.out.println("Átadom az adatokat");
		model.put("modifyEmployees", employees.stream()
				.filter(e -> e.getId() == id)
				.findFirst()
				.get());
		return "modifyemployee";
	}

	// Majd ha módosította akkor frissíti és visszatér az employees oldalra

}
