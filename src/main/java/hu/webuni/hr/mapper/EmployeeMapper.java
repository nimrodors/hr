package hu.webuni.hr.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.webuni.hr.dto.EmployeeDto;
import hu.webuni.hr.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

	List<EmployeeDto> employeeToEmployeeDto(List<Employee> employes);
	
	List<Employee> dtosToEmployees(List<EmployeeDto> employees);
	
	@Mapping(target = "company.employees", ignore = true)
	EmployeeDto employeeToDto(Employee employee);
	
	Employee employeDtoToEmployee(EmployeeDto employeeDto);

}
