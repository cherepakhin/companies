package ru.perm.v.companies.mapper;

import org.junit.jupiter.api.Test;
import ru.perm.v.companies.dto.EmployeeDto;
import ru.perm.v.companies.entity.CompanyEntity;
import ru.perm.v.companies.entity.EmployeeEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeMapperTest {
    Long N = 1L;
    String FIRST_NAME = "FIRST_NAME";
    String LAST_NAME = "LAST_NAME";
    String FATHER_NAME = "FATHER_NAME";
    LocalDate BIRTHDAY = LocalDate.of(1999, 12, 31);
    CompanyEntity companyEntity = new CompanyEntity(100L);

    @Test
    void toDTO() {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setN(N);
        employeeEntity.setFirstname(FIRST_NAME);
        employeeEntity.setLastname(LAST_NAME);
        employeeEntity.setFathername(FATHER_NAME);
        employeeEntity.setBirthday(BIRTHDAY);
        employeeEntity.setCompanyEntity(companyEntity);

        EmployeeMapper employeeMapper = new EmployeeMapper();
        EmployeeDto dto = employeeMapper.toDTO(employeeEntity);

        assertEquals(N, dto.getN());
        assertEquals(FIRST_NAME, dto.getFirstname());
        assertEquals(LAST_NAME, dto.getLastname());
        assertEquals(FATHER_NAME, dto.getFathername());
        assertEquals("31.12.1999", dto.getBirthday());
        assertEquals(companyEntity.getN(), dto.getCompanyN());
    }

    @Test
    void toEntity() {
    }
}