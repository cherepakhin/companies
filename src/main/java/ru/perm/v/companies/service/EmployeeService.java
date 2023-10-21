package ru.perm.v.companies.service;

import ru.perm.v.companies.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto create(EmployeeDto employee);

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

}
