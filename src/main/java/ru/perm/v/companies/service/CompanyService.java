package ru.perm.v.companies.service;

import ru.perm.v.companies.entity.CompanyEntity;

import java.util.List;

public interface CompanyService {
    List<CompanyEntity> getAll();
    CompanyEntity getByN(Long n);
    List<CompanyEntity> getByShortName(String name);
}
