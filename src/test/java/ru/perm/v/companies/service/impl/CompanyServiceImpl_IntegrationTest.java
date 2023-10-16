package ru.perm.v.companies.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.perm.v.companies.dto.CompanyDto;
import ru.perm.v.companies.entity.CompanyEntity;
import ru.perm.v.companies.service.CompanyService;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
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
        CompanyEntity company = companyService.getByN(0L);
        assertEquals(0, company.getN());
    }

    @Test
    public void getByNotExistN() {
        CompanyEntity company = companyService.getByN(-100L);
        assertEquals(-1, company.getN());
    }

    @Test
    public void getByN_check_director() {
        CompanyEntity company = companyService.getByN(3L);
        assertEquals(2L, company.getDirector().getN());
    }

    @Test
    public void getByN_by_N1() {
        CompanyEntity company = companyService.getByN(1L);
        assertEquals(1, company.getN());
    }

    @Test
    public void getByShortName() {
        List<CompanyEntity> companiesIds = companyService.getByShortName("shortname_1");
        for (CompanyEntity c : companiesIds) {
            System.out.println(c.getN());
        }
        assertEquals(1, companiesIds.size());
    }
}
