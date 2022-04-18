package hu.webuni.hr.service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import hu.webuni.hr.model.Company;
import hu.webuni.hr.model.Employee;


@Service
public class CompanyService {
	
	private Map<Long, Company> companies = new HashMap<>();

	private List<Employee> forestCoEmployee = new ArrayList<>();
	private List<Employee> mineCo = new ArrayList<>();
	private List<Employee> milk = new ArrayList<>();

	{

		forestCoEmployee.add(
				new Employee(1L, "Pocak", "Brummogás", 100, LocalDateTime.of(2021, Month.SEPTEMBER, 1, 12, 24, 00)));
		forestCoEmployee.add(
				new Employee(2L, "Fürtös", "Házörző", 242, LocalDateTime.of(1998, Month.DECEMBER, 21, 7, 31, 00)));
		forestCoEmployee
				.add(new Employee(3L, "Marci", "Dorombol", 198, LocalDateTime.of(1997, Month.APRIL, 5, 23, 07, 00)));

		mineCo.add(new Employee(1L, "Hapci", "Bányász", 437, LocalDateTime.of(1997, Month.APRIL, 5, 23, 07, 00)));

		companies.put(1L, new Company(1L, "ForestCo", "Rivendell", forestCoEmployee));
		companies.put(2L, new Company(2L, "MineCo", "Coal", mineCo));
		companies.put(3L, new Company(3L, "MilkCo", "Cow", milk));
	}
	
	public List<Company> getAll() {
		return new ArrayList<>(companies.values());
	}
	
	public Company getById(long id) {
		return companies.get(id);
	}

	public Map<Long, Company> getCompanies() {
		return companies;
	}

	public void setCompanies(Map<Long, Company> companies) {
		this.companies = companies;
	}
	
}
