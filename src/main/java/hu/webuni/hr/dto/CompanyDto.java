package hu.webuni.hr.dto;

import java.util.List;

public class CompanyDto {
	
	private long companyNumber;
	private String name;
	private String address;
	private List<EmployeeDto> employeeDto;
	
	public CompanyDto() {
	}
	public CompanyDto(long companyNumber, String name, String address, List<EmployeeDto> employeeDto) {
		super();
		this.companyNumber = companyNumber;
		this.name = name;
		this.address = address;
		this.employeeDto = employeeDto;
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
	public List<EmployeeDto> getEmployeeDto() {
		return employeeDto;
	}
	public void setEmployeeDto(List<EmployeeDto> employeeDto) {
		this.employeeDto = employeeDto;
	}
	
	
}
