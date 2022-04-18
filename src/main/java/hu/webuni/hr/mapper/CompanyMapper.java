package hu.webuni.hr.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.webuni.hr.dto.CompanyDto;
import hu.webuni.hr.model.Company;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

	List<CompanyDto> companiesToDto(List<Company> company);
	
	CompanyDto companyToDto(Company company);
	
	Company dtoToCompany(CompanyDto companyDto);
}
