package hu.webuni.hr.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Company {
	
	@Id
	@GeneratedValue
	private long companyNumber;
	private String name;
	private String address;
	
	@OneToMany(mappedBy = "company")
	private List<Employee> employees = new ArrayList<>();
	
	public Company() {
		super();
	}
	public Company(long companyNumber, String name, String address, List<Employee> employees) {
		super();
		this.companyNumber = companyNumber;
		this.name = name;
		this.address = address;
		this.employees = employees;
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
		return employees;
	}
	public void setEmployee(List<Employee> employees) {
		this.employees = employees;
	}
	
	public void addEmployee(Employee employee) {
		employee.setCompany(this);
		if(this.employees == null)
			this.employees = new ArrayList<>();
		this.employees.add(employee);
	}
}
