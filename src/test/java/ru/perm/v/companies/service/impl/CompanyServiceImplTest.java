package ru.perm.v.companies.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.perm.v.companies.entity.CompanyEntity;
import ru.perm.v.companies.service.CompanyService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CompanyServiceImplTest {

    @Autowired
    CompanyService companyService;

    @Test
    public void getAll() {
        List<CompanyEntity> companies = companyService.getAll();
        assertEquals(1, companies.size());
    }

    @Test
    public void getByN() {
        CompanyEntity company = companyService.getByN(0L);
        assertEquals(0, company.getN());
    }

    @Test
    public void getByNotExistN() {
        CompanyEntity company = companyService.getByN(-100L);
        assertEquals(-1, company.getN());
    }
}
