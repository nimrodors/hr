package hu.webuni.hr.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import hu.webuni.hr.model.Company;
import hu.webuni.hr.model.Employee;
import hu.webuni.hr.repository.CompanyRepository;
import hu.webuni.hr.service.CompanyService;

@SpringBootTest
@AutoConfigureTestDatabase
public class CompanyControllerIT {

	@Autowired
	CompanyService companyService;

	@Autowired
	CompanyRepository companyRepository;

	// Minden teszt előtt törli az adatbázist
//	@BeforeEach
//	public void init() {
//		companyRepository.deleteAll();
//	}

	@Test
	void testAddEmployeeToCompany() throws Exception {
		List<Employee> employees = new ArrayList<>();
		Employee e = new Employee("Kicsi Maki", "Fészek lakó", 2149,
				LocalDateTime.of(2021, Month.SEPTEMBER, 1, 12, 24, 00), null);
		Employee e2 = new Employee("Fűben Maki", "Fa alatt alvó", 1123,
				LocalDateTime.of(2001, Month.AUGUST, 23, 22, 49, 00), null);
		employees.add(e);
		employees.add(e2);
		Company saveCompany = companyService.save(new Company("Maki", "Popó utca 10", employees));
		Employee eC = saveCompany.getEmployees().stream().reduce((first, second) -> second).orElse(null);
		assertThat(eC).isEqualTo(e2);
	}

	@Test
	void testReplaceAllEmployees() throws Exception {
		List<Employee> employees = new ArrayList<>();
		List<Employee> changeEmployees = new ArrayList<>();
		{
			Employee e = new Employee("Kicsi Maki", "Fészek lakó", 2149,
					LocalDateTime.of(2021, Month.SEPTEMBER, 1, 12, 24, 00), null);
			Employee e2 = new Employee("Fűben Maki", "Fa alatt alvó", 1123,
					LocalDateTime.of(2001, Month.AUGUST, 23, 22, 49, 00), null);
			employees.add(e);
			employees.add(e2);
		}

		{
			Employee e = new Employee("Elalélt Jack", "Rammstein hallgató", 5789,
					LocalDateTime.of(2016, Month.FEBRUARY, 25, 9, 11, 20), null);
			Employee e2 = new Employee("Tüzes Béla", "Gyufa Készítő", 4783,
					LocalDateTime.of(2003, Month.NOVEMBER, 15, 11, 35, 55), null);
			changeEmployees.add(e);
			changeEmployees.add(e2);
		}
		Company company = companyService.save(new Company("Road", "Ramstein körút 11", employees));
		List<Employee> e1 = company.getEmployees();
		company = companyService.replaceEmployees(company.getCompanyNumber(), changeEmployees);
		List<Employee> e2 = company.getEmployees();
		assertThat(e1).isNotEqualTo(e2);
	}
}
