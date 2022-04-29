package hu.webuni.hr.web;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

import hu.webuni.hr.dto.CompanyDto;
import hu.webuni.hr.dto.EmployeeDto;
import hu.webuni.hr.mapper.CompanyMapper;
import hu.webuni.hr.mapper.EmployeeMapper;
import hu.webuni.hr.model.Company;
import hu.webuni.hr.model.Employee;
import hu.webuni.hr.service.CompanyService;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

	@Autowired
	CompanyService companyService;

	@Autowired
	CompanyMapper companyMapper;

	@Autowired
	EmployeeMapper employeeMapper;

//	private Map<Long, CompanyDto> companies = new HashMap<>();
//
//	private List<EmployeeDto> forestCoEmployee = new ArrayList<>();
//	private List<EmployeeDto> mineCo = new ArrayList<>();
//	private List<EmployeeDto> milk = new ArrayList<>();
//
//	{
//
//		forestCoEmployee.add(
//				new EmployeeDto(1L, "Pocak", "Brummogás", 100, LocalDateTime.of(2021, Month.SEPTEMBER, 1, 12, 24, 00)));
//		forestCoEmployee.add(
//				new EmployeeDto(2L, "Fürtös", "Házörző", 242, LocalDateTime.of(1998, Month.DECEMBER, 21, 7, 31, 00)));
//		forestCoEmployee
//				.add(new EmployeeDto(3L, "Marci", "Dorombol", 198, LocalDateTime.of(1997, Month.APRIL, 5, 23, 07, 00)));
//
//		mineCo.add(new EmployeeDto(1L, "Hapci", "Bányász", 437, LocalDateTime.of(1997, Month.APRIL, 5, 23, 07, 00)));
//
//		companies.put(1L, new CompanyDto(1L, "ForestCo", "Rivendell", forestCoEmployee));
//		companies.put(2L, new CompanyDto(2L, "MineCo", "Coal", mineCo));
//		companies.put(3L, new CompanyDto(3L, "MilkCo", "Cow", milk));
//	}

	@GetMapping
	public List<CompanyDto> getAll(@RequestParam(required = false) Boolean full) {
		List<Company> companies = companyService.findAll();

		if (isFull(full)) {
			List<CompanyDto> companiesToDto = companyMapper.companiesToDto(companies);
			return companiesToDto; 
		} else {
			return companyMapper.companiesToDtosNoEmployees(companies);
		}
	}

	private CompanyDto mapCompanyWithoutEmployee(CompanyDto c) {
		return new CompanyDto(c.getCompanyNumber(), c.getName(), c.getAddress(), null);
	}

	private boolean isFull(Boolean full) {
		return full != null && full;
	}

	@GetMapping("/{id}")
	public CompanyDto getById(@PathVariable long id, @RequestParam(required = false) Boolean full) {
		Company company = companyService.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		if (isFull(full)) {
			return companyMapper.companyToDto(company);
		} else {
			return companyMapper.companyToDtoNoEmployees(company);
		}
	}

	@PostMapping
	public CompanyDto createCompanyDto(@RequestBody CompanyDto companyDto) {
		return companyMapper.companyToDto(companyService.save(companyMapper.dtoToCompany(companyDto)));
	}

	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable long id) {
		// companies.remove(id);
		companyService.delete(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CompanyDto> modifyCompanyDto(@PathVariable long id, @RequestBody CompanyDto companyDto) {
		// beállítjuk neki hogy mit módosítson
		companyDto.setCompanyNumber(id);
		Company updateCompany = companyService.update(companyMapper.dtoToCompany(companyDto));
		if (updateCompany == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(companyMapper.companyToDto(updateCompany));
	}

	// Ha a full false akkor ne jelenjen meg az adott Cég dolgozója
//	@GetMapping("/fullemployee")
//	public List<CompanyDto> getFullEmployee(@RequestParam boolean full) {
//		if (full)
//			return new ArrayList<>(companies.values());
//		else
//			return companies.values().stream()
//					.map(c -> new CompanyDto(c.getCompanyNumber(), c.getName(), c.getAddress(), null))
//					.collect(Collectors.toList());
//	}

	// Megadott céghez hozzáadunk egy új alkalmazottat
	@PostMapping("/newcompanyemployee/{id}")
	public CompanyDto addNewEmployee(@PathVariable long id, @RequestBody EmployeeDto employeeDto) {
		try {
			//return companyMapper.companyToDto(companyService.addEmployee(id, employeeMapper.employeDtoToEmployee(employeeDto)));
			//return companyMapper.employeeToDto(companyService.addEmployee(id, companyMapper.employeDtoToEmployee(employeeDto)));
			//return companyMapper.employeeToDto(companyService.addEmployee(id, companyMapper.employeDtoToEmployee(employeeDto)));
			return companyMapper.companyToDto(companyService.addEmployee(id, companyMapper.employeDtoToEmployee(employeeDto)));
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}/deleteemployee/{employeeId}")
	public CompanyDto deleteEmployee(@PathVariable long id, @PathVariable long employeeId) {
//		CompanyDto companyDto = companies.get(id);
		// miért nem működik a companyDto.getEmployeeDto().remove(employeeId)?
		// Azért mert a remove intet vár nekem pedig long típusom van. Castolni sem jó,
		// mert nem azt törli amit kéne.
//		companyDto.getEmployeeDto().removeIf(e -> e.getId() == employeeId);
		// companyDto.getEmployeeDto().remove((int) employeeId);
//		return companyDto;
		try {
			return companyMapper.companyToDto(companyService.deleteEmployee(id, employeeId));
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	// Kicsréli két cég alkalmazottait
//	@PutMapping("/{idOne}/changeemployee/{idTwo}")
//	public List<CompanyDto> changeEmployee(@PathVariable long idOne, @PathVariable long idTwo) {
//		List<EmployeeDto> employeeOne = companies.get(idOne).getEmployeeDto();
//		List<EmployeeDto> employeeTwo = companies.get(idTwo).getEmployeeDto();
//		System.out.println(companies.get(idTwo).getName());
//		companies.get(idTwo).setEmployeeDto(employeeOne);
//		companies.get(idOne).setEmployeeDto(employeeTwo);
//
//		return companies.values().stream().collect(Collectors.toList());
//	}

	// Lecseréljük egy adott id-val rendelkező cég összes alkalmazottját
	@PutMapping("/{id}/employees")
	public CompanyDto replaceAllemployees(@PathVariable long id, @RequestBody List<EmployeeDto> employeeDtos) {
//		CompanyDto companyDto = companies.get(id);
//
//		companyDto.setEmployeeDto(employeeDtos);
//		return companyDto;
		try {
			return companyMapper.companyToDto(companyService.replaceEmployees(id, employeeMapper.dtosToEmployees(employeeDtos)));
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/certainSalary/{salary}")
	public List<CompanyDto> getCompanysEmployeeWithCertainSalary(@PathVariable int salary){
		List<Company> Company = companyService.getCompanyEmployeeWithCertainSalary(salary);
		return companyMapper.companiesToDto(Company);
	}
}
