package ru.perm.v.companies.service;

import ru.perm.v.companies.dto.CompanyDto;
import ru.perm.v.companies.entity.CompanyEntity;
import ru.perm.v.companies.entity.EmployeeEntity;

import java.util.List;

public interface CompanyService {
    List<CompanyDto> getAll();
    CompanyEntity getByN(Long n);
    List<CompanyEntity> getByShortName(String name);
}
