package hu.webuni.hr.dto;

import java.time.LocalDateTime;

public class EmployeeDto {
	
	private Long id;
	private String name;
	private String post;
	private int salary;
	private LocalDateTime localDateTime;
	
	public EmployeeDto() {
	}
	
	public EmployeeDto(Long id, String name, String post, int salary, LocalDateTime localDateTime) {
		super();
		this.id = id;
		this.name = name;
		this.post = post;
		this.salary = salary;
		this.localDateTime = localDateTime;
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
