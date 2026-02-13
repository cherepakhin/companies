package ru.perm.v.companies.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanyDtoTest {

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testToString() {
        EmployeeDto employeeDto = new EmployeeDto();
        CompanyDto companyDto = new CompanyDto(1L, "SHORTNAME", "FULLNAME", "INN", "OGRN","ADDRESS_POST","ADDRESS_UR", employeeDto);
        String result = companyDto.toString();

        assertEquals("CompanyDto{n=1, shortName='SHORTNAME', fullName='FULLNAME', inn='INN', ogrn='OGRN', addressPost='ADDRESS_POST', addressUr='ADDRESS_UR'}", result.toString());
    }

    @Test
    void testEquals() {
        EmployeeDto employeeDto = new EmployeeDto();
        CompanyDto companyDto1 = new CompanyDto(1L, "SHORTNAME", "FULLNAME", "INN", "OGRN","ADDRESS_POST","ADDRESS_UR", employeeDto);
        CompanyDto companyDto2 = new CompanyDto(1L, "SHORTNAME", "FULLNAME", "INN", "OGRN","ADDRESS_POST","ADDRESS_UR", employeeDto);

        boolean result = companyDto1.equals(companyDto2);

        assertTrue(result);
    }

    @Test
    void testHashCode() {
        CompanyDto companyDto = new CompanyDto();
        int result = companyDto.hashCode();
        assertNotEquals(0, result);
    }
}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme