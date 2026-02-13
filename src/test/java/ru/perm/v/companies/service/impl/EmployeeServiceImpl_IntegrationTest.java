package ru.perm.v.companies.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.perm.v.companies.dto.EmployeeDto;
import ru.perm.v.companies.service.EmployeeService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class EmployeeServiceImpl_IntegrationTest {

    @Autowired
    EmployeeService employeeService;

    @Test
    public void getAll() {
        List<EmployeeDto> empls = employeeService.getAll();

        assertEquals(5, empls.size());
        // check sorting
        assertEquals(0L, empls.get(0).getN());
        assertEquals(1L, empls.get(1).getN());
        assertEquals(2L, empls.get(2).getN());
        assertEquals(3L, empls.get(3).getN());
        assertEquals(4L, empls.get(4).getN());
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
    public void getByFirstNameCheckOrder() {
        List<EmployeeDto> empls = employeeService.getByFirstName("firstname");

        assertEquals(4, empls.size());
        assertEquals("firstname_2", empls.get(0).getFirstname());
        assertEquals("firstname_3", empls.get(1).getFirstname());
        assertEquals("firstname_30", empls.get(2).getFirstname());
        assertEquals("firstname_20", empls.get(3).getFirstname());
    }

    @Test
    void findByLastNameLikeOrderByN() {
        String lastName = "0";
        List<EmployeeDto> empls = employeeService.findByLastnameLikeOrderByN(lastName);
        assertEquals(2, empls.size());

        List<Long> nn = empls.stream().map(EmployeeDto::getN).collect(Collectors.toList());
        assertEquals(List.of(3L, 4L), nn);
        List<String> names = empls.stream().map(EmployeeDto::getFirstname).collect(Collectors.toList());
        assertEquals(List.of("firstname_30", "firstname_20"), names);
    }

    @Test
    void findByLastnameLikeOrderByNAsc() {
        String lastName = "lastname_2";
        List<EmployeeDto> empls = employeeService.findByLastnameOrderByNAsc(lastName);
        assertEquals(2, empls.size());
        assertEquals("lastname_2", empls.get(0).getLastname());
        assertEquals("lastname_20", empls.get(1).getLastname());
    }

    @Test
    void findByLastnameLikeOrderByNDesc() {
        String lastName = "lastname_2";

        List<EmployeeDto> empls = employeeService.findByLastnameOrderByNDesc(lastName);

        assertEquals(2, empls.size());
        assertEquals("lastname_20", empls.get(0).getLastname());
        assertEquals("lastname_2", empls.get(1).getLastname());
    }

    @Test
    void findByLastnameOrderByLastnameAsc() {
        String lastName = "lastname_2";

        List<EmployeeDto> empls = employeeService.findByLastnameOrderByLastnameAsc(lastName);

        assertEquals(1, empls.size());
        assertEquals("lastname_2", empls.get(0).getLastname());
    }

    @Test
    void getByFirstNameOrderByColumn() {
        String firstName = "firstname_2";

        List<EmployeeDto> empls = null;
        try {
            empls = employeeService.getByFirstNameOrderByColumn(firstName, "lastname");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertEquals(2, empls.size());
        assertEquals("lastname_2", empls.get(0).getLastname());
        assertEquals("lastname_20", empls.get(1).getLastname());
    }

    @Test
    void getByFirstNameOrderByEnumColumnLastname() {
        String firstName = "firstname_2";

        List<EmployeeDto> empls = employeeService.getByFirstNameOrderByEnumColumn(firstName, EmployeeService.SORT_COLUMN.lastname);

        assertEquals(2, empls.size());
        assertEquals("lastname_2", empls.get(0).getLastname());
        assertEquals("lastname_20", empls.get(1).getLastname());
    }

    @Test
    void getByFirstNameOrderByEnumColumnFirstname() {
        String firstName = "firstname_2";

        List<EmployeeDto> empls = employeeService.getByFirstNameOrderByEnumColumn(firstName, EmployeeService.SORT_COLUMN.firstname);

        assertEquals(2, empls.size());
        assertEquals("lastname_2", empls.get(0).getLastname());
        assertEquals("lastname_20", empls.get(1).getLastname());
    }

    @Test
    void findByLastnameOrderByFirstnameAsc() {
        String lastName = "lastname_2";

        List<EmployeeDto> empls = employeeService.findByLastnameOrderByFirstnameAsc(lastName);

        assertEquals(1, empls.size());
        assertEquals("lastname_2", empls.get(0).getLastname());
    }
}