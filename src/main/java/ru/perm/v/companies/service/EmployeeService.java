package ru.perm.v.companies.service;

import ru.perm.v.companies.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeService {
    List<EmployeeEntity> getAll();

    EmployeeEntity getByN(Long n);
    List<EmployeeEntity> getByFirstName(String name);
}
