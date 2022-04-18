package hu.webuni.hr.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.webuni.hr.dto.EmployeeDto;
import hu.webuni.hr.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

	List<EmployeeDto> employeeToEmployeeDto(List<Employee> employes);

	EmployeeDto employeeToDto(Employee employee);
	
	Employee employeDtoToEmployee(EmployeeDto employeeDto);

}
