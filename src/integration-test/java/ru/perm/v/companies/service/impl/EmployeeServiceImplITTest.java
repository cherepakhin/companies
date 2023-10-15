package ru.perm.v.companies.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.perm.v.companies.entity.EmployeeEntity;
import ru.perm.v.companies.service.EmployeeService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//TODO: add fake data for test
@SpringBootTest
public class EmployeeServiceImplITTest {

    @Autowired
    EmployeeService employeeService;

    @Test
    public void getAll() {
        List<EmployeeEntity> empls = employeeService.getAll();
        assertEquals(4, empls.size());
    }

    @Test
    public void getByN() {
        EmployeeEntity employee = employeeService.getByN(0L);
        assertEquals(0L, employee.getN());
    }

    @Test
    public void getByForNotExistN() {
        EmployeeEntity employee = employeeService.getByN(-100L);
        assertEquals(-1L, employee.getN());
    }

}