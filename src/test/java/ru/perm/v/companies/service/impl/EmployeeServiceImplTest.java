package ru.perm.v.companies.service.impl;

import org.junit.jupiter.api.Test;
import ru.perm.v.companies.dto.EmployeeDto;
import ru.perm.v.companies.entity.EmployeeEntity;
import ru.perm.v.companies.repository.EmployeeRepository;
import ru.perm.v.companies.service.EmployeeService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployeeServiceImplTest {

    EmployeeRepository employeeRepository = mock(EmployeeRepository.class);

    @Test
    void convertFromEntityToDto() {
        Long N = 100L;
        String FIRST_NAME = "FIRST_NAME_100";
        String LAST_NAME = "LAST_NAME_100";
        String FATHER_NAME = "FATHER_NAME_100";
        LocalDate BIRTHDAY = LocalDate.of(2023, 12, 1);
        EmployeeEntity entity = new EmployeeEntity(N, FIRST_NAME, LAST_NAME, FATHER_NAME, BIRTHDAY);

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
        LocalDate BIRTHDAY = LocalDate.of(2023, 12, 1);
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
        try {
            assertEquals(1L, employeeService.getByN(1L).getN());
        } catch (Exception e) {
            fail();
        }
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
        String FIRST_NAME = "FIRST_NAME_100";
        String LAST_NAME = "LAST_NAME_100";
        String FATHER_NAME = "FATHER_NAME_100";
        LocalDate BIRTHDAY = LocalDate.of(2023, 12, 1);
        Long NEXT_N = 100L;
        EmployeeDto dto = new EmployeeDto(NEXT_N, FIRST_NAME, LAST_NAME, FATHER_NAME, "01/12/2023");
        when(employeeRepository.getNextN()).thenReturn(NEXT_N);
        EmployeeEntity entityForSave = new EmployeeEntity(NEXT_N, FIRST_NAME, LAST_NAME, FATHER_NAME, BIRTHDAY);
        EmployeeEntity entitySaved = new EmployeeEntity(NEXT_N, FIRST_NAME, LAST_NAME, FATHER_NAME, BIRTHDAY);
        when(employeeRepository.save(entityForSave)).thenReturn(entitySaved);

        EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository);

        EmployeeDto createdEmployee = employeeService.create(dto);

        assertEquals(dto, createdEmployee);
        verify(employeeRepository, times(1)).getNextN();
        verify(employeeRepository, times(1)).save(entityForSave);
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
        Long N_1 = 1L;
        employee1.setN(N_1);

        EmployeeEntity employee2 = new EmployeeEntity();
        Long N_2 = 2L;
        employee2.setN(N_2);

        when(employeeRepository.findByLastnameOrderByLastnameAsc(LAST_NAME))
                .thenReturn(List.of(employee1, employee2));
        EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository);

        List<EmployeeDto> dtos = employeeService.findByLastnameOrderByLastnameAsc(LAST_NAME);

        assertNotNull(dtos);
        assertEquals(2, dtos.size());
        assertEquals(N_1, dtos.get(0).getN());
        assertEquals(N_2, dtos.get(1).getN());
        verify(employeeRepository, times(1))
                .findByLastnameOrderByLastnameAsc(LAST_NAME);
    }

    @Test
    void getNextN() {
        when(employeeRepository.getNextN())
                .thenReturn(100L);
        EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository);
        assertEquals(100, employeeService.getNextN());
    }

    @Test
    void deleteByN() {
        Long N = 100L;
        when(employeeRepository.findById(N)).thenReturn(Optional.of(new EmployeeEntity()));
        doNothing().when(employeeRepository).deleteById(N);
        EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository);
        try {
            employeeService.deleteByN(N);
            verify(employeeRepository, times(1))
                    .deleteById(N);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void update() {
        // prepare
        Long N = 100L;
        String FIRST_NAME = "OLD_FIRST_NAME_100";
        String LAST_NAME = "LAST_NAME_100";
        String FATHER_NAME = "FATHER_NAME_100";
        String BIRTHDAY = "01/12/2023";
        LocalDate BIRTHDAY_DATE = LocalDate.of(2023, 12, 1);
        EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository);
        EmployeeEntity employeeEntity = new EmployeeEntity(N, FIRST_NAME, LAST_NAME, FATHER_NAME, BIRTHDAY_DATE);
        // employeeEntityUpdated only for check test
        EmployeeEntity employeeEntityUpdated = new EmployeeEntity(N, FIRST_NAME, LAST_NAME, FATHER_NAME, BIRTHDAY_DATE);
        when(employeeRepository.save(employeeEntity)).thenReturn(employeeEntityUpdated);
        EmployeeDto employeeDto = new EmployeeDto(N, FIRST_NAME, LAST_NAME, FATHER_NAME, BIRTHDAY);
        // do test
        EmployeeDto updatedEmployeeDto = employeeService.update(employeeDto);
        // verify
        assertEquals(employeeDto, updatedEmployeeDto);
        verify(employeeRepository, times(1)).save(employeeEntity);
    }
}
