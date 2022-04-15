package hu.webuni.hr.model;

import java.util.List;

public class Company {
	
	private long companyNumber;
	private String name;
	private String address;
	private List<Employee> employee;
	public Company() {
		super();
	}
	public Company(long companyNumber, String name, String address, List<Employee> employee) {
		super();
		this.companyNumber = companyNumber;
		this.name = name;
		this.address = address;
		this.employee = employee;
	}
	public long getCompanyNumber() {
		return companyNumber;
	}
	public void setCompanyNumber(long companyNumber) {
		this.companyNumber = companyNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<Employee> getEmployee() {
		return employee;
	}
	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}
	
}
