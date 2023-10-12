package ru.perm.v.companies.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.perm.v.companies.entity.EmployeeEntity;
import ru.perm.v.companies.service.EmployeeService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//TODO: add fake data for test
@SpringBootTest
class EmployeeServiceImplTest {

    @Autowired
    EmployeeService employeeService;

    @Test
    void getAll() {
        List<EmployeeEntity> empls = employeeService.getAll();
        assertEquals(1, empls.size());
    }

    @Test
    void getByN() {
        EmployeeEntity employee = employeeService.getByN(0L);
        assertEquals(0L, employee.getN());
    }
}