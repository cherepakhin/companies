package ru.perm.v.companies.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.perm.v.companies.service.CompanyService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CompanyServiceImplTest {

    @Autowired
    CompanyService companyService;

    @Test
    public void getAll() {
        assertEquals(1,companyService.getAll().size());
    }
}
