package ru.perm.v.companies.service;

import ru.perm.v.companies.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> getAll();

    EmployeeDto getByN(Long n);
    List<EmployeeDto> getByFirstName(String name);
    List<EmployeeDto> getByFirstNameOrderByColumn(String name, String columnName) throws Exception;
    List<EmployeeDto> getByFirstNameOrderByEnumColumn(String name, SORT_COLUMN column);
    List<EmployeeDto> findByLastnameLikeOrderByN(String lastName);
    List<EmployeeDto> findByLastnameOrderByNDesc(String lastName);
    List<EmployeeDto> findByLastnameOrderByNAsc(String lastName);
    List<EmployeeDto> findByLastnameLikeOrderByNDesc(String lastName);
    List<EmployeeDto> findByLastnameOrderByLastnameAsc(String lastName);
    EmployeeDto create(EmployeeDto employee) throws Exception;
    List<EmployeeDto> findByLastnameOrderByFirstnameAsc(String lastName);

    enum SORT_COLUMN { n, firstname, lastname, fathername, birthday, company_n };
    boolean isSortColumnValid(String columnName);
    EmployeeDto update(EmployeeDto dto) throws Exception;
}
