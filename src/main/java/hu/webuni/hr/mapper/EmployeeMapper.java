package hu.webuni.hr.mapper;

import java.util.List;

import hu.webuni.hr.dto.EmployeeDto;
import hu.webuni.hr.model.Employee;

public interface EmployeeMapper {
	
	List<EmployeeDto> employeeToEmployeeDto(List<Employee> employess);
}
