package ru.perm.v.companies.rest;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.perm.v.companies.dto.CompanyDto;
import ru.perm.v.companies.service.CompanyService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//TODO: delete, modify
public class CompanyRestTest {

    CompanyService companyService = mock(CompanyService.class);

    @Test
    public void getById() throws Exception {
        Long ID = 100L;

        CompanyRest rest = new CompanyRest(companyService);
//        CompanyEntity company1 = new CompanyEntity();
//        company1.setN(ID);
//        CompanyDto company2 = CompanyMapper.fromEntityToDto(company1);
        CompanyDto company1 = new CompanyDto();
        company1.setN(ID);
        when(companyService.getByN(ID)).thenReturn(company1);

        ResponseEntity<CompanyDto> responseCompany = rest.getById(ID);

        assertEquals(new ResponseEntity<>(company1, HttpStatus.OK), responseCompany);
        assertEquals(HttpStatus.OK, responseCompany.getStatusCode());
        assertEquals(company1, responseCompany.getBody());
    }

    @Test
    public void getAll() {
        CompanyRest rest = new CompanyRest(companyService);
        CompanyDto company1 = new CompanyDto(1L);
        CompanyDto company2 = new CompanyDto(2L);

        when(companyService.getAll()).thenReturn(List.of(company1, company2));

        assertEquals(List.of(company1, company2), rest.getAll().getBody());
    }

    @Test
    public void exceptionOnGetById() throws Exception {
        Long ID = 100L;
        CompanyRest rest = new CompanyRest(companyService);

        when(companyService.getByN(ID)).thenThrow(new Exception(("NOT FOUND")));

        ResponseEntity response = rest.getById(ID);
        assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());
        assertEquals("Company not found id=100", response.getBody());
    }

}