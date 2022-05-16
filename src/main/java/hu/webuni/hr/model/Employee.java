package hu.webuni.hr.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import org.springframework.stereotype.Service;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue
	private Long id;
	@NotEmpty(message = "Nincs Név Megadva!")
	private String name;
	@NotEmpty(message = "Nincs Beosztás Megadva!")
	private String post;
	@Positive(message = "Fizetés Csak Pozitív Szám Lehet")
	private int salary;
	@PastOrPresent
	private LocalDateTime localDateTime;
	
	@ManyToOne
	private Company company;

	public Employee() {
	}

	public Employee(Long id, String name, String post, int salary, LocalDateTime localDateTime) {
		this.id = id;
		this.name = name;
		this.post = post;
		this.salary = salary;
		this.localDateTime = localDateTime;
	}
	
	public Employee(@NotEmpty(message = "Nincs Név Megadva!") String name,
			@NotEmpty(message = "Nincs Beosztás Megadva!") String post,
			@Positive(message = "Fizetés Csak Pozitív Szám Lehet") int salary,
			@PastOrPresent LocalDateTime localDateTime, Company company) {
		super();
		this.name = name;
		this.post = post;
		this.salary = salary;
		this.localDateTime = localDateTime;
		this.company = company;
	}

	public int getMonth() {
		int month;
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime from = this.getLocalDateTime();

		month = (int) ChronoUnit.MONTHS.between(from, now);
		return month;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}
