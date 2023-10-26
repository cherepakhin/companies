package ru.perm.v.companies.service;

import ru.perm.v.companies.dto.CompanyDto;
import ru.perm.v.companies.dto.EmployeeDto;

import java.util.List;

//TODO: modify
public interface CompanyService {
    List<CompanyDto> getAll();
    CompanyDto getByN(Long n) throws Exception;
    List<CompanyDto> getByShortName(String name);
    void deleteById(Long id);
    CompanyDto update(CompanyDto companyDto) throws Exception;
}
