package ru.perm.v.companies.service.impl;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.perm.v.companies.dto.EmployeeDto;
import ru.perm.v.companies.service.EmployeeService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

//TODO: add fake data for test
@SpringBootTest
@Transactional
@Disabled
public class EmployeeServiceImpl_IntegrationTest {

    @Autowired
    EmployeeService employeeService;

    @Test
    public void getAll() {
        List<EmployeeDto> empls = employeeService.getAll();
        assertEquals(6, empls.size());
    }

    @Test
    public void getByN() {
        EmployeeDto employee = employeeService.getByN(0L);
        assertEquals(0L, employee.getN());
    }

    @Test
    public void getByForNotExistN() {
        EmployeeDto employee = employeeService.getByN(-100L);
        assertEquals(-1L, employee.getN());
    }

    @Test
    public void getByFirstName() {
        List<EmployeeDto> empls = employeeService.getByFirstName("firstname_20");
        assertEquals(1, empls.size());
        assertEquals("firstname_20", empls.get(0).getFirstname());
    }

    @Test
    void findByLastNameLikeOrderByN() {
        String lastName = "0";
        List<EmployeeDto> empls = employeeService.findByLastnameLikeOrderByN(lastName);
        assertEquals(2, empls.size());

        // demo map
        List<Long> nn = empls.stream().map(EmployeeDto::getN).collect(Collectors.toList());
        assertEquals(List.of(4L, 5L), nn);
        List<String> names = empls.stream().map(EmployeeDto::getFirstname).collect(Collectors.toList());
        assertEquals(List.of("firstname_30", "firstname_20"), names);
    }

    @Test
    void findByLastnameLikeOrderByNAsc() {
        String lastName = "lastname_1";
        List<EmployeeDto> empls = employeeService.findByLastnameOrderByNAsc(lastName);
        assertEquals(1, empls.size());
        assertEquals(lastName, empls.get(0).getLastname());
    }
}