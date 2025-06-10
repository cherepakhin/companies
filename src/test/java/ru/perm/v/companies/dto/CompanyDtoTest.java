package ru.perm.v.companies.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
//import static org.mockito.Mockito.*;
class CompanyDtoTest {
    @Mock
    ru.perm.v.companies.dto.EmployeeDto director;
    @InjectMocks
    ru.perm.v.companies.dto.CompanyDto companyDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testToString(){
        java.lang.String result = companyDto.toString();
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testEquals(){
        boolean result = companyDto.equals("o");
        Assertions.assertEquals(true, result);
    }

    @Test
    void testHashCode(){
        int result = companyDto.hashCode();
        Assertions.assertEquals(0, result);
    }
}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme