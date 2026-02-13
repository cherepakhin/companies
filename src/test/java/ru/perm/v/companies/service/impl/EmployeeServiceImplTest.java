package ru.perm.v.companies.service.impl;

import org.junit.jupiter.api.Test;
import ru.perm.v.companies.dto.EmployeeDto;
import ru.perm.v.companies.entity.CompanyEntity;
import ru.perm.v.companies.entity.EmployeeEntity;
import ru.perm.v.companies.repository.EmployeeRepository;
import ru.perm.v.companies.service.CompanyService;
import ru.perm.v.companies.service.EmployeeService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeServiceImplTest {

    EmployeeRepository mockEmployeeRepository = mock(EmployeeRepository.class);
    CompanyService mockCompanyService = mock(CompanyService.class);

    @Test
    public void getAll() {
        EmployeeService employeeService = new EmployeeServiceImpl(mockEmployeeRepository, mockCompanyService);
        EmployeeEntity employee1 = new EmployeeEntity(1L);
        CompanyEntity companyEntity = new CompanyEntity(1L);
        employee1.setCompanyEntity(companyEntity);
        EmployeeEntity employee2 = new EmployeeEntity(2L);
        employee2.setCompanyEntity(companyEntity);
        when(mockEmployeeRepository.findAllByOrderByNAsc()).thenReturn(List.of(employee1, employee2));

        List<EmployeeDto> empls = employeeService.getAll();

        assertEquals(2, empls.size());
    }

    @Test
    void getByN() {
        EmployeeService employeeService = new EmployeeServiceImpl(mockEmployeeRepository, mockCompanyService);
        EmployeeEntity employeeEntity1 = new EmployeeEntity(1L);
        CompanyEntity companyEntity = new CompanyEntity(1L);
        employeeEntity1.setCompanyEntity(companyEntity);
        when(mockEmployeeRepository.findById(1L)).thenReturn(Optional.of(employeeEntity1));

        assertEquals(1L, employeeService.getByN(1L).getN());
    }
}
