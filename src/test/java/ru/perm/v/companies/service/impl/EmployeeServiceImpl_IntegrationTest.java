package ru.perm.v.companies.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.perm.v.companies.dto.EmployeeDto;
import ru.perm.v.companies.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

//TODO: add config with fake data for test
@SpringBootTest
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
    public void findAllByOrderByNAsc() {
        List<EmployeeDto> empls = employeeService.findAllByOrderByNAsc();

        assertEquals(6, empls.size());

        //compare array
        assertArrayEquals(List.of(0L, 1L, 2L, 3L, 4L, 5L).toArray(),
                empls.stream().map(EmployeeDto::getN).toArray());

        Long[] arr = {0L, 1L, 2L, 3L, 4L, 5L};
        assertArrayEquals(arr,
                empls.stream().map(EmployeeDto::getN).toArray());

        List<Long> listN = empls.stream().map(EmployeeDto::getN).collect(Collectors.toList());
        //compare list
        assertEquals(List.of(0L, 1L, 2L, 3L, 4L, 5L), listN);
    }

    @Test
    public void getByFirstName() {
        List<EmployeeDto> empls = employeeService.getByFirstName("firstname_20");
        assertEquals(1, empls.size());
        assertEquals("firstname_20", empls.get(0).getFirstname());
    }

    @Test
    public void findByLastnameOrderByNDesc() {
        String lastName = "lastname_2";
        List<EmployeeDto> empls = employeeService.findByLastnameOrderByNDesc(lastName);
        for (EmployeeDto empl : empls) {
            System.out.println(empl.getN());
        }
        assertEquals(2, empls.size());
        Long[] expectedNN = {2L, 5L};
        // мой вариант
        assertArrayEquals(expectedNN, empls.stream().map(EmployeeDto::getN).collect(Collectors.toList()).toArray());
        // так короче
        assertArrayEquals(expectedNN, empls.stream().map(EmployeeDto::getN).toArray());
        // можно через List, а не через Array
        List<Long> nn = empls.stream().map(EmployeeDto::getN).collect(Collectors.toList());
        assertEquals(List.of(2L, 5L), nn);
        // демо map
        List<String> names = empls.stream().map(EmployeeDto::getFirstname).collect(Collectors.toList());
        assertEquals(List.of("firstname_2", "firstname_20"), names);
    }

    @Test
    public void findByLastnameContainingOrderByNAsc() {
        String lastName = "lastname_2";

        List<EmployeeDto> empls = employeeService.findByLastnameLikeOrderByNAsc(lastName);

        assertEquals(2, empls.size());

        assertEquals(2, empls.get(0).getN());
        assertEquals(5, empls.get(1).getN());

        assertEquals("lastname_2", empls.get(0).getLastname());
        assertEquals("lastname_20", empls.get(1).getLastname());
    }
}