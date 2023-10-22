package ru.perm.v.companies.service.impl;

import org.junit.jupiter.api.Test;
import ru.perm.v.companies.dto.EmployeeDto;
import ru.perm.v.companies.entity.EmployeeEntity;
import ru.perm.v.companies.repository.EmployeeRepository;
import ru.perm.v.companies.service.EmployeeService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class EmployeeServiceImplTest {

    EmployeeRepository employeeRepository = mock(EmployeeRepository.class);

    @Test
    void convertFromEntityToDto() {
        Long N = 100L;
        String FIRST_NAME = "FIRST_NAME_100";
        String LAST_NAME = "LAST_NAME_100";
        String FATHER_NAME = "FATHER_NAME_100";
        LocalDate BIRTHDAY = LocalDate.of(2023, 12,1);
        EmployeeEntity entity = new EmployeeEntity(N, FIRST_NAME, LAST_NAME,FATHER_NAME, BIRTHDAY);

        EmployeeDto dto = EmployeeServiceImpl.convertFromEntityToDto(entity);

        assertEquals(N, dto.getN());
        assertEquals(FIRST_NAME, dto.getFirstname());
        assertEquals(LAST_NAME, dto.getLastname());
        assertEquals(FATHER_NAME, dto.getFathername());
        assertEquals("01/12/2023", dto.getBirthday());
    }

    @Test
    void convertFromDtoToEntity() {
        Long N = 100L;
        String FIRST_NAME = "FIRST_NAME_100";
        String LAST_NAME = "LAST_NAME_100";
        String FATHER_NAME = "FATHER_NAME_100";
        LocalDate BIRTHDAY = LocalDate.of(2023, 12,1);
        EmployeeDto dto = new EmployeeDto(N, FIRST_NAME, LAST_NAME, FATHER_NAME, "01/12/2023");
        EmployeeEntity entity = EmployeeServiceImpl.convertFromDtoToEntity(dto);

        assertEquals(N, entity.getN());
        assertEquals(FIRST_NAME, entity.getFirstname());
        assertEquals(LAST_NAME, entity.getLastname());
        assertEquals(FATHER_NAME, entity.getFathername());
        assertEquals(BIRTHDAY, entity.getBirthday());
    }

    @Test
    void getByN() {
        EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository);
        EmployeeEntity employee1 = new EmployeeEntity(1L);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee1));

        assertEquals(1L, employeeService.getByN(1L).getN());
    }

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
    void create() {
        Long N = 100L;
        String FIRST_NAME = "FIRST_NAME_100";
        String LAST_NAME = "LAST_NAME_100";
        String FATHER_NAME = "FATHER_NAME_100";
        LocalDate BIRTHDAY = LocalDate.of(2023, 12,1);
        EmployeeDto dto = new EmployeeDto(N, FIRST_NAME, LAST_NAME, FATHER_NAME, "01/12/2023");
        EmployeeEntity entity = new EmployeeEntity(N, FIRST_NAME, LAST_NAME, FATHER_NAME, BIRTHDAY);
        when(employeeRepository.save(entity)).thenReturn(entity);

        EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository);

        EmployeeDto createdEmployee = employeeService.create(dto);

        assertEquals(dto, createdEmployee);
    }

    @Test
    public void findByLastnameLikeOrderByN() {
        String LAST_NAME = "LAST_NAME_100";

        EmployeeEntity employee1 = new EmployeeEntity();
        Long ID_1 = 1L;
        employee1.setN(ID_1);

        EmployeeEntity employee2 = new EmployeeEntity();
        Long ID_2 = 2L;
        employee2.setN(ID_2);

        when(employeeRepository.findByLastnameContainingOrderByNAsc(LAST_NAME))
                .thenReturn(List.of(employee1, employee2));
        EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository);

        List<EmployeeDto> dtos = employeeService.findByLastnameLikeOrderByN(LAST_NAME);

        assertNotNull(dtos);
        assertEquals(2, dtos.size());
        assertEquals(ID_1, dtos.get(0).getN());
        assertEquals(ID_2, dtos.get(1).getN());
    }

    @Test
    void findByLastnameOrderByNAsc() {
        String LAST_NAME = "LAST_NAME_100";

        EmployeeEntity employee1 = new EmployeeEntity();
        Long ID_1 = 1L;
        employee1.setN(ID_1);

        EmployeeEntity employee2 = new EmployeeEntity();
        Long ID_2 = 2L;
        employee2.setN(ID_2);

        when(employeeRepository.findByLastnameOrderByNAsc(LAST_NAME))
                .thenReturn(List.of(employee1, employee2));
        EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository);

        List<EmployeeDto> dtos = employeeService.findByLastnameOrderByNAsc(LAST_NAME);

        assertNotNull(dtos);
        assertEquals(2, dtos.size());
        assertEquals(ID_1, dtos.get(0).getN());
        assertEquals(ID_2, dtos.get(1).getN());
        verify(employeeRepository, times(1))
                .findByLastnameOrderByNAsc(LAST_NAME);
    }

    @Test
    void findByLastnameLikeOrderByNDesc() {
        String LAST_NAME = "LAST_NAME_100";

        EmployeeEntity employee1 = new EmployeeEntity();
        Long ID_1 = 1L;
        employee1.setN(ID_1);

        EmployeeEntity employee2 = new EmployeeEntity();
        Long ID_2 = 2L;
        employee2.setN(ID_2);

        when(employeeRepository.findByLastnameOrderByNDesc(LAST_NAME))
                .thenReturn(List.of(employee1, employee2));
        EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository);

        List<EmployeeDto> dtos = employeeService.findByLastnameLikeOrderByNDesc(LAST_NAME);

        assertNotNull(dtos);
        assertEquals(2, dtos.size());
        assertEquals(ID_1, dtos.get(0).getN());
        assertEquals(ID_2, dtos.get(1).getN());
        verify(employeeRepository, times(1))
                .findByLastnameOrderByNDesc(LAST_NAME);
    }

    @Test
    void findByLastnameOrderByLastnameAsc() {
        String LAST_NAME = "LAST_NAME_100";

        EmployeeEntity employee1 = new EmployeeEntity();
        Long ID_1 = 1L;
        employee1.setN(ID_1);

        EmployeeEntity employee2 = new EmployeeEntity();
        Long ID_2 = 2L;
        employee2.setN(ID_2);

        when(employeeRepository.findByLastnameOrderByLastnameAsc(LAST_NAME))
                .thenReturn(List.of(employee1, employee2));
        EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository);

        List<EmployeeDto> dtos = employeeService.findByLastnameOrderByLastnameAsc(LAST_NAME);

        assertNotNull(dtos);
        assertEquals(2, dtos.size());
        assertEquals(ID_1, dtos.get(0).getN());
        assertEquals(ID_2, dtos.get(1).getN());
        verify(employeeRepository, times(1))
                .findByLastnameOrderByLastnameAsc(LAST_NAME);
    }
}
