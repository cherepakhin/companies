package ru.perm.v.companies.rest;

import org.junit.jupiter.api.Test;
import ru.perm.v.companies.dto.EmployeeDto;
import ru.perm.v.companies.service.EmployeeService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeRestTest {

    @Test
    public void getAll() {
        Long N1 = 1L;
        String FIRST_NAME_1 = "FIRST_NAME_1";
        Long N2 = 2L;
        String FIRST_NAME_2 = "FIRST_NAME_2";
        EmployeeDto employee1 = new EmployeeDto();
        employee1.setN(N1);
        employee1.setFirstname(FIRST_NAME_1);
        EmployeeDto employee2 = new EmployeeDto();
        employee2.setN(N2);
        employee2.setFirstname(FIRST_NAME_2);

        EmployeeService employeeService = mock(EmployeeService.class);
        when(employeeService.getAll()).thenReturn(List.of(employee1, employee2));

        EmployeeRest employeeRest = new EmployeeRest(employeeService);
        List<EmployeeDto> receivedEmpls = employeeRest.getAll().getBody();

        assertEquals(2, receivedEmpls.size());
        EmployeeDto receivedEmpls0 = receivedEmpls.get(0);
        assertEquals(
                new EmployeeDto(1L, "FIRST_NAME_1", "", "", "1990/01/01"),
                receivedEmpls0);
    }
}