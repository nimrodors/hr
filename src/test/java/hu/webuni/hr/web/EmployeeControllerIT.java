package hu.webuni.hr.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

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
		EmployeeDto newEmployee = new EmployeeDto(3L, "Tapsi Hapsi", "Üreg Felügyelő", 6578,
				LocalDateTime.of(2021, Month.SEPTEMBER, 1, 12, 24, 00));

		createEmployee(newEmployee);
		List<EmployeeDto> employeesAfter = getAllEmployee();

		// hozzáadás előtt tartalmazza az elemeket
		assertThat(employeesAfter.subList(0, employeesBefore.size())).usingRecursiveFieldByFieldElementComparator()
				.containsExactlyElementsOf(employeesBefore);

		// hozzáadás utáni elemek
		assertThat(employeesAfter.get(employeesAfter.size() - 1)).usingRecursiveComparison().isEqualTo(newEmployee);
	}

	private void createEmployee(EmployeeDto newEmployee) {
		webTestClient.post().uri(BASE_URI).bodyValue(newEmployee).exchange().expectStatus().isOk();

	}

	private List<EmployeeDto> getAllEmployee() {
		List<EmployeeDto> responseList = webTestClient.get().uri(BASE_URI).exchange().expectStatus().isOk()
				.expectBodyList(EmployeeDto.class).returnResult().getResponseBody();

		Collections.sort(responseList, (e1, e2) -> Long.compare(e1.getId(), e2.getId()));
		return responseList;
	}

	@Test
	void testModifyEmployee() throws Exception {
		// Lekérem azt amit módosítani szeretnék
		EmployeeDto employeeDto = getEmployee(0);
		//lekérem az összes employeet, majd kicserélem a az új elemet a régire
		List<EmployeeDto> employeDtoList = getAllEmployee();
		
		// Megcsinálom a módosítottat
		EmployeeDto modifyEmployeeDto = new EmployeeDto(1L, "Sarolt", "mászkáló", 100,
				LocalDateTime.of(2021, Month.SEPTEMBER, 1, 12, 24, 00));
		// Objektum összehaslítás, szerintem az kell ide

		//Ezzel megvizsgálom hogy a két objektum különbözik e egymástól
		assertNotSame(employeeDto, modifyEmployeeDto);
		
		//Csinálok egy új listát, hogy megnézzem, hogy a két lista különbözik egymástól
		List<EmployeeDto> newEmployeeDtosList = getAllEmployee();
		newEmployeeDtosList.set(0, modifyEmployeeDto);
		
		assertNotSame(employeDtoList, newEmployeeDtosList);
	}

	private EmployeeDto getEmployee(int i) {
		List<EmployeeDto> responseList = webTestClient.get().uri(BASE_URI).exchange().expectStatus().isOk()
				.expectBodyList(EmployeeDto.class).returnResult().getResponseBody();

		EmployeeDto responseEmployee = responseList.get(i);
		return responseEmployee;
	}
}
