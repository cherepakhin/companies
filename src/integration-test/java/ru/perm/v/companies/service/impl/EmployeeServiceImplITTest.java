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
        assertEquals(6, empls.size());
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

    @Test
    public void getByFirstName() {
        List<EmployeeEntity> empls = employeeService.getByFirstName("firstname_20");
        assertEquals(1, empls.size());
        assertEquals("firstname_20", empls.get(0).getFirstname());
    }

//    @Test
//    void findByLastnameLikeOrderByN() {
//        String lastName = "0";
//        List<EmployeeEntity> empls = employeeService.findByLastNameLikeOrderByN(lastName);
//        assertEquals(2, empls.size());
//    }

}