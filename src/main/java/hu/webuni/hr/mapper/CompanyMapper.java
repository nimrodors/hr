package hu.webuni.hr.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import hu.webuni.hr.dto.CompanyDto;
import hu.webuni.hr.dto.EmployeeDto;
import hu.webuni.hr.model.Company;
import hu.webuni.hr.model.Employee;


@Mapper(componentModel = "spring")
public interface CompanyMapper {

	List<CompanyDto> companiesToDto(List<Company> companies);

	@IterableMapping(qualifiedByName = "summary")
	List<CompanyDto> companiesToDtosNoEmployees(List<Company> companies);
	
	List<Company> companieDtosToCompany(List<CompanyDto> companies);

	@InheritInverseConfiguration
	CompanyDto companyToDto(Company company);

	@Mapping(target = "employees", ignore = true)
	@Named("summary")
	CompanyDto companyToDtoNoEmployees(Company company);

	Company dtoToCompany(CompanyDto companyDto);

	@Mapping(target = "company", ignore = true)
	EmployeeDto employeeToDto(Employee employee);
	
	@InheritInverseConfiguration
	Employee employeDtoToEmployee(EmployeeDto employeeDto);

}
