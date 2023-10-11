package ru.perm.v.companies.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.perm.v.companies.entity.CompanyEntity;
import ru.perm.v.companies.repository.CompanyRepository;
import ru.perm.v.companies.service.CompanyService;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    private CompanyEntity nullCompany;

    @Override
    public List<CompanyEntity> getAll() {
        return companyRepository.findAll();
    }

    @Override
    public CompanyEntity getByN(Long n) {
        Optional<CompanyEntity> res = companyRepository.findById(n);
        return res.orElseGet(this::getNotFonded);
    }

    private CompanyEntity getNotFonded() {
        if (nullCompany == null) {
            nullCompany = new CompanyEntity();
            nullCompany.setN(-1L);
        }
        return nullCompany;
    }
}
