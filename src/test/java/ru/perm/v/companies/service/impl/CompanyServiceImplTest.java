package ru.perm.v.companies.service.impl;

import org.junit.jupiter.api.Test;
import ru.perm.v.companies.entity.CompanyEntity;
import ru.perm.v.companies.repository.CompanyRepository;
import ru.perm.v.companies.service.CompanyService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CompanyServiceImplTest {
    CompanyRepository companyRepository = mock(CompanyRepository.class);

    @Test
    public void getAll() {
        CompanyService companyService = new CompanyServiceImpl(companyRepository);
        CompanyEntity company1 = new CompanyEntity(1);
        CompanyEntity company2 = new CompanyEntity(2);
        when(companyRepository.findAll()).thenReturn(List.of(company1, company2));

        assertEquals(2, companyService.getAll().size());
    }
}
