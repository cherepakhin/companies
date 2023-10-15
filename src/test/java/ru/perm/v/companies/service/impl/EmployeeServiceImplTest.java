package ru.perm.v.companies.service.impl;

import org.junit.jupiter.api.Test;
import ru.perm.v.companies.entity.EmployeeEntity;
import ru.perm.v.companies.repository.EmployeeRepository;
import ru.perm.v.companies.service.EmployeeService;

import java.util.List;

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

        List<EmployeeEntity> empls = employeeService.getAll();

        assertEquals(2, empls.size());
    }

    @Test
    void getByN() {
    }
}
