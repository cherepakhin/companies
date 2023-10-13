package ru.perm.v.companies.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.perm.v.companies.entity.CompanyEntity;
import ru.perm.v.companies.service.CompanyService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CompanyServiceImplIntegrationTest {

    @Autowired
    CompanyService companyService;

    @Test
    public void simple() {
        assertEquals(0, 0);
    }
}
