package ru.perm.v.companies.rest.bito;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.perm.v.companies.dto.CompanyDto;
import ru.perm.v.companies.dto.EmployeeDto;
import ru.perm.v.companies.rest.CompanyRest;
import ru.perm.v.companies.service.CompanyService;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Test generated Bito in VS Code
 */
public class CompanyRestBitoTest {

    @Mock
    private CompanyService companyService;

    @InjectMocks
    private CompanyRest companyRest;

    public CompanyRestBitoTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll_ReturnsListOfCompanyDto() {
        // Arrange
        // CompanyRest companyRest = new CompanyRest(companyService);
        
        EmployeeDto director100 = new EmployeeDto();
        director100.setN(100L);

        List<CompanyDto> companies = new ArrayList<>();
        companies.add(
            new CompanyDto(1L, "Shortname 1", "Fullname 1", "INN 1", "OGRN 1", "Address Post 1", "Address UR 1", director100));
        companies.add(
            new CompanyDto(2L, "Shortname 2", "Fullname 2", "INN 2", "OGRN 2", "Address Post 2", "Address UR 2", director100));
        
        when(companyService.getAll()).thenReturn(companies);
        
        // Act
        ResponseEntity<List<CompanyDto>> response = companyRest.getAll();
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<CompanyDto> dtos = response.getBody();
        assertNotNull(dtos);
        assertEquals(2, dtos.size());
        assertEquals("Shortname 1", dtos.get(0).getShortname());
        assertEquals("Fullname 1", dtos.get(0).getFullname());
        assertEquals("INN 1", dtos.get(0).getInn());
        assertEquals("OGRN 1", dtos.get(0).getOgrn());
        assertEquals("Address Post 1", dtos.get(0).getAddressPost());
        assertEquals("Address UR 1", dtos.get(0).getAddressUr());
        assertEquals(director100, dtos.get(0).getDirector());
        
        assertEquals("Shortname 2", dtos.get(1).getShortname());
        assertEquals("Fullname 2", dtos.get(1).getFullname());
        assertEquals("INN 2", dtos.get(1).getInn());
        assertEquals("OGRN 2", dtos.get(1).getOgrn());
        assertEquals("Address Post 2", dtos.get(1).getAddressPost());
        assertEquals("Address UR 2", dtos.get(1).getAddressUr());
        assertEquals(director100, dtos.get(1).getDirector());
        
        verify(companyService, times(1)).getAll();
    }

    @Test
    void testGetByIdPositive() throws Exception {
        Long companyId = 1L;
        CompanyDto companyDto = new CompanyDto(); // Create a sample CompanyDto object

        when(companyService.getByN(companyId)).thenReturn(companyDto);

        ResponseEntity<CompanyDto> response = companyRest.getById(companyId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(companyDto, response.getBody());

        verify(companyService, times(1)).getByN(companyId);
    }
    
    @Test
    void testGetByIdNegative() throws Exception {
        Long companyId = 1L;

        when(companyService.getByN(companyId)).thenThrow(new Exception("Company not found"));

        ResponseEntity<CompanyDto> response = companyRest.getById(companyId);

        assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());
        assertEquals("Company not found id=1",response.getBody());

        verify(companyService, times(1)).getByN(companyId);
    }    
}