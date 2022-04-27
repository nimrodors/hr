package hu.webuni.hr.service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hu.webuni.hr.model.Company;
import hu.webuni.hr.model.Employee;
import hu.webuni.hr.repository.CompanyRepository;
import hu.webuni.hr.repository.EmployeeRepository;
import net.bytebuddy.asm.Advice.Return;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	public Company save(Company company) {
		return companyRepository.save(company);
	}

	public Company update(Company company) {
		if (!companyRepository.existsById(company.getCompanyNumber()))
			return null;
		return companyRepository.save(company);
	}

	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	public Optional<Company> findById(long id) {
		return companyRepository.findById(id);
	}

	public void delete(long id) {
		companyRepository.deleteById(id);
	}

	public Company addEmployee(long id, Employee employee) {
		Company company = companyRepository.findById(id).get();
		company.addEmployee(employee);
		employeeRepository.save(employee);
		return company;
	}

	public Company deleteEmployee(long companyId, long employeeId) {
		// itt megkeresem, hogy van e ilye olyan company, employee
		Company company = companyRepository.findById(companyId).get();
		Employee employee = employeeRepository.findById(employeeId).get();
		employee.setCompany(null);
		company.getEmployee().remove(employee);
		// a törlések után újra rámentek, hogy már nem legyen benne az employee a
		// companyben
		employeeRepository.save(employee);
		return company;
	}

	// össze Employeet lecserélem
	public Company replaceEmployees(long id, List<Employee> employees) {
		Company company = companyRepository.findById(id).get();
		for (Employee employee : company.getEmployee()) {
			employee.setCompany(null);
		}
		company.getEmployee().clear();

		for (Employee employee : employees) {
			company.addEmployee(employee);
			employeeRepository.save(employee);
		}

		return company;
	}

//	private Map<Long, Company> companies = new HashMap<>();
//
//	private List<Employee> forestCoEmployee = new ArrayList<>();
//	private List<Employee> mineCo = new ArrayList<>();
//	private List<Employee> milk = new ArrayList<>();
//
//	{
//
//		forestCoEmployee.add(
//				new Employee(1L, "Pocak", "Brummogás", 100, LocalDateTime.of(2021, Month.SEPTEMBER, 1, 12, 24, 00)));
//		forestCoEmployee.add(
//				new Employee(2L, "Fürtös", "Házörző", 242, LocalDateTime.of(1998, Month.DECEMBER, 21, 7, 31, 00)));
//		forestCoEmployee
//				.add(new Employee(3L, "Marci", "Dorombol", 198, LocalDateTime.of(1997, Month.APRIL, 5, 23, 07, 00)));
//
//		mineCo.add(new Employee(1L, "Hapci", "Bányász", 437, LocalDateTime.of(1997, Month.APRIL, 5, 23, 07, 00)));
//
//		companies.put(1L, new Company(1L, "ForestCo", "Rivendell", forestCoEmployee));
//		companies.put(2L, new Company(2L, "MineCo", "Coal", mineCo));
//		companies.put(3L, new Company(3L, "MilkCo", "Cow", milk));
//	}

//	public List<Company> getAll() {
//		return new ArrayList<>(companies.values());
//	}
//	
//	public Company getById(long id) {
//		return companies.get(id);
//	}
//	
//	public Company createCompany(Company company) {
//		companies.put(company.getCompanyNumber(), company);
//		return company;
//	}
//	
//	public void removeCompany(long id) {
//		companies.remove(id);
//	}
//	
//	public Company addEmployeeToCompany(long id, Employee employee) {
//		if(!companies.containsKey(id))
//			throw new NoSuchElementException();
//		companies.get(id).getEmployee().add(employee);
//		return companies.get(id);
//	}
//
//	public Map<Long, Company> getCompanies() {
//		return companies;
//	}
//
//	public void setCompanies(Map<Long, Company> companies) {
//		this.companies = companies;
//	}

}
