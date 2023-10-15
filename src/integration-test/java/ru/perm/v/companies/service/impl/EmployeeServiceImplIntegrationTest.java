package ru.perm.v.companies.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.perm.v.companies.entity.EmployeeEntity;
import ru.perm.v.companies.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

//TODO: add fake data for test
@SpringBootTest
public class EmployeeServiceImplIntegrationTest {

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

    @Test
    void findByLastNameLikeOrderByN() {
        String lastName = "0";
        List<EmployeeEntity> empls = employeeService.findByLastNameLikeOrderByN(lastName);
        assertEquals(2, empls.size());

        // demo map
        List<Long> nn = empls.stream().map(EmployeeEntity::getN).collect(Collectors.toList());
        assertEquals(List.of(4L, 5L), nn);
        List<String> names = empls.stream().map(EmployeeEntity::getFirstname).collect(Collectors.toList());
        assertEquals(List.of("firstname_30", "firstname_20"), names);
    }

}