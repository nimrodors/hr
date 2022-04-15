package hu.webuni.hr.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.webuni.hr.dto.EmployeeDto;
import hu.webuni.hr.model.Employee;

@Mapper
public interface EmployeeMapper {

	List<EmployeeDto> employeeToEmployeeDto(List<Employee> employes);

	EmployeeDto employeeToDta(Employee employee);

}
