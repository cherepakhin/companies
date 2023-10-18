package ru.perm.v.companies.service;

import ru.perm.v.companies.dto.CompanyDto;

import java.util.List;

public interface CompanyService {
    List<CompanyDto> getAll();
    CompanyDto getByN(Long n) throws Exception;
    List<CompanyDto> getByShortName(String name);
    void deleteById(Long id);
}
