package hu.webuni.hr;

import java.time.LocalDateTime;
import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.webuni.hr.model.Employee;
import hu.webuni.hr.service.SalaryService;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {
	
	@Autowired
	Employee sarolt;
	
	@Autowired
	SalaryService salaryService;

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		sarolt = new Employee(1L, "Sarolt", "child", 100, LocalDateTime.of(2015, Month.JANUARY, 1, 10, 10, 30));
		
		System.out.println("Emelés előtt: " + sarolt.getSalary());
		
		sarolt = salaryService.newSalary(sarolt);
		
		System.out.println("Emelés után: " + sarolt.getSalary());
		
	}

}
