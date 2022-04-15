package hu.webuni.hr.web;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.dto.EmployeeDto;
import hu.webuni.hr.model.Employee;
import hu.webuni.hr.service.EmployeeService;
import hu.webuni.hr.service.AbstractEmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
//	private Map<Long, EmployeeDto> employees = new HashMap<>();
//	
//	{
//		employees.put(1L, new EmployeeDto(1L, "Sarolt", "gangszta", 100, LocalDateTime.of(2021, Month.SEPTEMBER, 1, 12, 24, 00)));
//		employees.put(2L, new EmployeeDto(2L, "Csuti", "miracle", 250, LocalDateTime.of(1985, Month.DECEMBER, 13, 23, 8, 00)));
//		
//	}
//	
//	@GetMapping
//	public List<EmployeeDto> getAll() {
//		return new ArrayList<>(employees.values());
//	}
//	
//	@GetMapping("/{id}")
//	public ResponseEntity<EmployeeDto> getById(@PathVariable long id){
//		EmployeeDto employeeDto = employees.get(id);
//		if(employeeDto != null) 
//			return ResponseEntity.ok(employeeDto);
//		else
//			return ResponseEntity.notFound().build();
//	}
//	
//	//új létrehozása
//	@PostMapping
//	public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
//		employees.put(employeeDto.getId(), employeeDto);
//		return employeeDto;
//	}
//	
//	//Módosítás
//	@PutMapping("/{id}")
//	public ResponseEntity<EmployeeDto> modifyEmployee(@PathVariable long id, @RequestBody @Valid EmployeeDto employeeDto) {
//		if(!employees.containsKey(id))
//			return ResponseEntity.notFound().build();
//		employeeDto.setId(id);
//		employees.put(id, employeeDto);
//		return ResponseEntity.ok(employeeDto);
//	}
//	
//	//Meglévő törlése
//	@DeleteMapping("/{id}")
//	public void deleteEmployee(@PathVariable long id) {
//		employees.remove(id);
//	}
//	
//	//bizonyos fizetéssel rendelkezők kilistázása
//	@GetMapping("/bigsalary")
//	public List<EmployeeDto> getEmployeesWithBiggerSalary(@RequestParam int salary){
//		//List<EmployeeDto> salaryEmployee = new ArrayList<>();
//		
//		return new ArrayList<>(employees.values()
//				.stream()
//				.filter(s -> salary < s.getSalary())
//				.collect(Collectors.toList()));
//		
//		//salaryEmployee.forEach(n -> System.out.println(n.getName() + ", " + n.getSalary()));
//		//return salaryEmployee;
//	}
	
	@PutMapping("/{id}/increasedsalary/{employeeId}")
	public int increasedSalary(@RequestParam("value = employee") Employee employee) {
		int salary = 0;
		salary = employeeService.getPayRaisePercent(employee);
		return salary;

	}
	
}
