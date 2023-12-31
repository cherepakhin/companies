package ru.perm.v.companies.service;

import ru.perm.v.companies.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> getAll();

    EmployeeDto getByN(Long n) throws Exception;

    List<EmployeeDto> findAllByOrderByNAsc();

    List<EmployeeDto> getByFirstName(String name);

    List<EmployeeDto> findByLastnameLikeOrderByN(String lastName);

    List<EmployeeDto> findByLastnameLikeOrderByNAsc(String lastName);

    List<EmployeeDto> findByLastnameLikeOrderByNDesc(String lastName);

    List<EmployeeDto> findByLastnameOrderByNAsc(String lastName);

    List<EmployeeDto> findByLastnameOrderByNDesc(String lastName);

    List<EmployeeDto> findByLastnameOrderByLastnameAsc(String lastName);

    // TODO: add exception
    EmployeeDto create(EmployeeDto employee);

    // TODO: add exception
    EmployeeDto update(EmployeeDto employee);

    Long getNextN();

    // TODO: add exception
    void deleteByN(Long n) throws Exception;
}
