package hu.webuni.hr.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.dto.EmployeeDto;
import hu.webuni.hr.mapper.EmployeeMapper;
import hu.webuni.hr.model.Employee;
import hu.webuni.hr.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	EmployeeMapper employeeMapper;

//	private Map<Long, EmployeeDto> employees = new HashMap<>();
//	
//	{
//		employees.put(1L, new EmployeeDto(1L, "Sarolt", "gangszta", 100, LocalDateTime.of(2021, Month.SEPTEMBER, 1, 12, 24, 00)));
//		employees.put(2L, new EmployeeDto(2L, "Csuti", "miracle", 250, LocalDateTime.of(1985, Month.DECEMBER, 13, 23, 8, 00)));
//		
//	}
//	
	@GetMapping
	public List<EmployeeDto> getAll() {
		return employeeMapper.employeeToEmployeeDto(employeeService.getAll());
	}

//	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getById(@PathVariable long id) {
		Employee employee = employeeService.getById(id);
		if (employee != null)
			return ResponseEntity.ok(employeeMapper.employeeToDto(employee));
		else
			return ResponseEntity.notFound().build();
	}

//	
	// új létrehozása
	@PostMapping
	public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
		return employeeMapper
				.employeeToDto(employeeService.createEmployee(employeeMapper.employeDtoToEmployee(employeeDto)));
	}

//	
	// Módosítás
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> modifyEmployee(@PathVariable long id, @RequestBody @Valid EmployeeDto employeeDto) {
		Employee updateEmployee = employeeService.modifyEmployee(id, employeeMapper.employeDtoToEmployee(employeeDto));
		updateEmployee.setId(id);
		
		try {
			EmployeeDto savedEmployeDto = employeeMapper.employeeToDto(updateEmployee);
			return ResponseEntity.ok(savedEmployeDto);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
//		if (updateEmployee == null)
//			return ResponseEntity.notFound().build();
//		else {
//			return ResponseEntity.ok(savedEmployeDto);
//		}
	}

//	
	// Meglévő törlése
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable long id) {
		employeeService.deleteEmployee(id);
	}

//	
	// bizonyos fizetéssel rendelkezők kilistázása
	@GetMapping("/bigsalary")
	public List<EmployeeDto> getEmployeesWithBiggerSalary(@RequestParam int salary) {
		List<EmployeeDto> salaryEmployee = employeeMapper
				.employeeToEmployeeDto(employeeService.
						getEmployeesWithBiggerSalary(salary));
		
//		return new ArrayList<>(employees.values()
//				.stream()
//				.filter(s -> salary < s.getSalary())
//				.collect(Collectors.toList()));

		// salaryEmployee.forEach(n -> System.out.println(n.getName() + ", " +
		// n.getSalary()));
		return salaryEmployee;
	}

	@PutMapping("/{id}/increasedsalary/{employeeId}")
	public int increasedSalary(@RequestParam("value = employee") EmployeeDto employeeDto) {
		int salary = 0;
		salary = employeeService.getPayRaisePercent(employeeMapper.employeDtoToEmployee(employeeDto));
		return salary;

	}
	
	@GetMapping("/post")
	public List<EmployeeDto> getEmployeesWithPost(@RequestParam String post){
		List<EmployeeDto> employeeDtosPost = employeeMapper
				.employeeToEmployeeDto(employeeService.getEmployeeWithPost(post));
		return employeeDtosPost;
	}
	
	@GetMapping("/letter")
	public List<EmployeeDto> getEmployeesCertainLetter(@RequestParam String letter) {
		List<EmployeeDto> emplyeeDtosCertainLetter = employeeMapper
				.employeeToEmployeeDto(employeeService.getEmployeeWithCertainLetter(letter));
		return emplyeeDtosCertainLetter;
	}
	
	@GetMapping("/from/{from}/to/{to}")
	public List<EmployeeDto> getEmployeesFromToDate(@PathVariable LocalDateTime from, @PathVariable LocalDateTime to) {
		List<EmployeeDto> employeeDtosFromToDate = employeeMapper
				.employeeToEmployeeDto(employeeService.getEmployeesBetweenDate(from, to));
		return employeeDtosFromToDate;
	}
}
