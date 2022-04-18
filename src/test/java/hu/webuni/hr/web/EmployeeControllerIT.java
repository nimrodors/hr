package hu.webuni.hr.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import hu.webuni.hr.dto.EmployeeDto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIT {
	
	private static final String BASE_URI = "/api/employees";
	
	@Autowired
	WebTestClient webTestClient;
	
	@Test
	void testThatCreatedEmployeeIsListed() throws Exception {
		List<EmployeeDto> employeesBefore = getAllEmployee();
		EmployeeDto newEmployee = new EmployeeDto(3L, "Tapsi Hapsi", "Üreg Felügyelő", 6578, LocalDateTime.of(2021, Month.SEPTEMBER, 1, 12, 24, 00));
		
		createEmployee(newEmployee);
		List<EmployeeDto> employeesAfter = getAllEmployee();
		
		//hozzáadás előtt tartalmazza az elemeket
		assertThat(employeesAfter.subList(0, employeesBefore.size()))
		.usingRecursiveFieldByFieldElementComparator()
		.containsExactlyElementsOf(employeesBefore);
		
		//hozzáadás utáni elemek
		assertThat(employeesAfter.get(employeesAfter.size()-1))
		.usingRecursiveComparison()
		.isEqualTo(newEmployee);
	}

	private void createEmployee(EmployeeDto newEmployee) {
		webTestClient
			.post()
			.uri(BASE_URI)
			.bodyValue(newEmployee)
			.exchange()
			.expectStatus()
			.isOk();
		
	}

	private List<EmployeeDto> getAllEmployee() {
		List<EmployeeDto> responseList = webTestClient
		.get()
		.uri(BASE_URI)
		.exchange()
		.expectStatus()
		.isOk()
		.expectBodyList(EmployeeDto.class)
		.returnResult()
		.getResponseBody();
		
		Collections.sort(responseList, (e1, e2) -> Long.compare(e1.getId(), e2.getId()));
		return responseList;
	}

}
