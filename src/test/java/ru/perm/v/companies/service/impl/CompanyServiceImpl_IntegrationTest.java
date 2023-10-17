package ru.perm.v.companies.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.perm.v.companies.dto.CompanyDto;
import ru.perm.v.companies.entity.CompanyEntity;
import ru.perm.v.companies.service.CompanyService;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Transactional
public class CompanyServiceImpl_IntegrationTest {

    @Autowired
    CompanyService companyService;

    @Test
    public void getAll() {
        List<CompanyDto> companies = companyService.getAll();
        assertNotNull(companies);
        assertEquals(4, companies.size());
    }

    @Test
    public void getByN() {
        CompanyDto company = companyService.getByN(0L);
        assertEquals(0, company.getN());
    }

    @Test
    public void getByNotExistN() {
        CompanyDto company = companyService.getByN(-100L);
        assertEquals(-1, company.getN());
    }

    @Test
    public void getByN_by_N1() {
        CompanyDto company = companyService.getByN(1L);
        assertEquals(1, company.getN());
    }

    @Test
    public void getByShortName() {
        List<CompanyDto> companies = companyService.getByShortName("shortname_1");
        for (CompanyDto c : companies) {
            System.out.println(c.getN());
        }
        assertEquals(1, companies.size());
    }
}
