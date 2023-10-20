package ru.perm.v.companies.service.impl;

import org.junit.jupiter.api.Test;
import ru.perm.v.companies.dto.EmployeeDto;
import ru.perm.v.companies.entity.EmployeeEntity;
import ru.perm.v.companies.repository.EmployeeRepository;
import ru.perm.v.companies.service.EmployeeService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeServiceImplTest {

    EmployeeRepository employeeRepository = mock(EmployeeRepository.class);

    @Test
    public void getAll() {
        EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository);
        EmployeeEntity employee1 = new EmployeeEntity(1L);
        EmployeeEntity employee2 = new EmployeeEntity(2L);
        when(employeeRepository.findAll()).thenReturn(List.of(employee1, employee2));

        List<EmployeeDto> empls = employeeService.getAll();

        assertEquals(2, empls.size());
    }

    @Test
    void getByN() {
        EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository);
        EmployeeEntity employee1 = new EmployeeEntity(1L);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee1));

        assertEquals(1L, employeeService.getByN(1L).getN());
    }
}
