package ru.perm.v.companies.service;

import ru.perm.v.companies.dto.EmployeeDto;

import java.util.List;

//TODO: modify, delete
public interface EmployeeService {
    List<EmployeeDto> getAll();
    EmployeeDto getByN(Long n);

    List<EmployeeDto> findAllByOrderByNAsc();

    List<EmployeeDto> getByFirstName(String name);

    List<EmployeeDto> findByLastnameLikeOrderByN(String lastName);
    List<EmployeeDto> findByLastnameLikeOrderByNAsc(String lastName);
    List<EmployeeDto> findByLastnameLikeOrderByNDesc(String lastName);

    List<EmployeeDto> findByLastnameOrderByNAsc(String lastName);
    List<EmployeeDto> findByLastnameOrderByNDesc(String lastName);
    List<EmployeeDto> findByLastnameOrderByLastnameAsc(String lastName);

    EmployeeDto create(EmployeeDto employee);
    EmployeeDto update(EmployeeDto employee);
    Long getNextN();
    void deleteByN(Long n);
}
