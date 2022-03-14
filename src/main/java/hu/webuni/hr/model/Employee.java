package hu.webuni.hr.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

@Service
public class Employee {
	
	private Long id;
	private String name;
	private String post;
	private int salary;
	private LocalDateTime localDateTime;
	
//	public Employee() {
//		super();
//	}

	public Employee(Long id, String name, String post, int salary, LocalDateTime localDateTime) {
		super();
		this.id = id;
		this.name = name;
		this.post = post;
		this.salary = salary;
		this.localDateTime = localDateTime;
	}

	public int getMonth() {
		int month;
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime from = this.getLocalDateTime();

		month = (int) ChronoUnit.MONTHS.between(now, from);
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
}
