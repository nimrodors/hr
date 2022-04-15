package hu.webuni.hr.web;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;
import hu.webuni.hr.dto.CompanyDto;
import hu.webuni.hr.dto.EmployeeDto;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

	private Map<Long, CompanyDto> companies = new HashMap<>();

	private List<EmployeeDto> forestCoEmployee = new ArrayList<>();
	private List<EmployeeDto> mineCo = new ArrayList<>();
	private List<EmployeeDto> milk = new ArrayList<>();

	{

		forestCoEmployee.add(
				new EmployeeDto(1L, "Pocak", "Brummogás", 100, LocalDateTime.of(2021, Month.SEPTEMBER, 1, 12, 24, 00)));
		forestCoEmployee.add(
				new EmployeeDto(2L, "Fürtös", "Házörző", 242, LocalDateTime.of(1998, Month.DECEMBER, 21, 7, 31, 00)));
		forestCoEmployee
				.add(new EmployeeDto(3L, "Marci", "Dorombol", 198, LocalDateTime.of(1997, Month.APRIL, 5, 23, 07, 00)));

		mineCo.add(new EmployeeDto(1L, "Hapci", "Bányász", 437, LocalDateTime.of(1997, Month.APRIL, 5, 23, 07, 00)));

		companies.put(1L, new CompanyDto(1L, "ForestCo", "Rivendell", forestCoEmployee));
		companies.put(2L, new CompanyDto(2L, "MineCo", "Coal", mineCo));
		companies.put(3L, new CompanyDto(3L, "MilkCo", "Cow", milk));
	}

	@GetMapping
	public List<CompanyDto> getAll() {
		return new ArrayList<>(companies.values());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CompanyDto> getById(@PathVariable long id) {
		CompanyDto companyDto = companies.get(id);
		if (companyDto != null)
			return ResponseEntity.ok(companyDto);
		else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public CompanyDto createCompanyDto(@RequestBody CompanyDto companyDto) {
		companies.put(companyDto.getCompanyNumber(), companyDto);
		return companyDto;
	}

	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable long id) {
		companies.remove(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CompanyDto> modifyCompanyDto(@PathVariable long id, @RequestBody CompanyDto companyDto) {
		if (!companies.containsKey(id))
			return ResponseEntity.notFound().build();

		companyDto.setCompanyNumber(id);
		companies.put(id, companyDto);
		return ResponseEntity.ok(companyDto);
	}

	// Ha a full false akkor ne jelenjen meg az adott Cég dolgozója
	@GetMapping("/fullemployee")
	public List<CompanyDto> getFullEmployee(@RequestParam boolean full) {
		List<CompanyDto> withoutEmployee = companies.values().stream().collect(Collectors.toList());
		if (full)
			return withoutEmployee;
		else
			withoutEmployee.forEach(c -> c.setEmployeeDto(null));

		return withoutEmployee;
	}

	// Megadott céghez hozzáadunk egy új alkalmazottat
	@PostMapping("/newcompanyemployee/{id}")
	public ResponseEntity<EmployeeDto> addNewEmployee(@PathVariable long id, @RequestBody EmployeeDto employeeDto) {
		if (!companies.containsKey(id))
			return ResponseEntity.notFound().build();
		else
			companies.get(id).getEmployeeDto().add(employeeDto);
		return ResponseEntity.ok(employeeDto);
	}

	@DeleteMapping("/{id}/deleteemployee/{employeeId}")
	public CompanyDto deleteEmployee(@PathVariable long id, @PathVariable long employeeId) {
		CompanyDto companyDto = companies.get(id);
		// miért nem működik a companyDto.getEmployeeDto().remove(employeeId)?
		// Azért mert a remove intet vár nekem pedig long típusom van. Castolni sem jó,
		// mert nem azt törli amit kéne.
		companyDto.getEmployeeDto().removeIf(e -> e.getId() == employeeId);
		//companyDto.getEmployeeDto().remove((int) employeeId);
		return companyDto;
	}
	
	//Kicsréli két cég alkalmazottait
	@PutMapping("/{idOne}/changeemployee/{idTwo}")
	public List<CompanyDto> changeEmployee(@PathVariable long idOne, @PathVariable long idTwo) {
		List<EmployeeDto> employeeOne = companies.get(idOne).getEmployeeDto();
		List<EmployeeDto> employeeTwo = companies.get(idTwo).getEmployeeDto();
		System.out.println(companies.get(idTwo).getName());
		companies.get(idTwo).setEmployeeDto(employeeOne);
		companies.get(idOne).setEmployeeDto(employeeTwo);

		return companies.values().stream().collect(Collectors.toList());
	}
}
