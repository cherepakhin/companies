package ru.perm.v.companies.service.impl;

import org.junit.jupiter.api.Test;
import ru.perm.v.companies.entity.CompanyEntity;
import ru.perm.v.companies.repository.CompanyRepository;
import ru.perm.v.companies.service.CompanyService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CompanyServiceImplTest {
    CompanyRepository companyRepository = mock(CompanyRepository.class);

    @Test
    public void getAll() {
        CompanyEntity company1 = new CompanyEntity(1);
        CompanyEntity company2 = new CompanyEntity(2);
        when(companyRepository.findAll()).thenReturn(List.of(company1, company2));
        CompanyService companyService = new CompanyServiceImpl(companyRepository);

        assertEquals(2, companyService.getAll().size());
    }

    @Test
    public void getAll_asList() {
        CompanyEntity company1 = new CompanyEntity(1);
        CompanyEntity company2 = new CompanyEntity(2);
        when(companyRepository.findAll()).thenReturn(Arrays.asList(company1, company2));
        CompanyService companyService = new CompanyServiceImpl(companyRepository);

        assertEquals(2, companyService.getAll().size());
    }


    @Test
    public void getByN() {
        long ID = 2;
        CompanyEntity COMPANY = new CompanyEntity(ID);
        when(companyRepository.findById(ID)).thenReturn(java.util.Optional.of(COMPANY));
        CompanyService companyService = new CompanyServiceImpl(companyRepository);
        assertEquals(COMPANY, companyService.getByN(ID));
    }

    @Test
    public void notFoundGetByN() {
        long ID = 2;
        when(companyRepository.findById(ID)).thenReturn(Optional.empty());
        CompanyService companyService = new CompanyServiceImpl(companyRepository);
        assertEquals(-1, companyService.getByN(ID).getN());
    }

}
