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

    private CompanyRepository companyRepository;

    private static CompanyEntity nullCompany = new CompanyEntity(-1);

    public CompanyServiceImpl(@Autowired CompanyRepository companyRepository) {
        this.companyRepository= companyRepository;
    }

    @Override
    public List<CompanyEntity> getAll() {
        return companyRepository.findAll();
    }

    @Override
    public CompanyEntity getByN(Long n) {
        Optional<CompanyEntity> res = companyRepository.findById(n);
        // если null:
        // можно просто ВЕРНУТЬ объект (orElse)
         return res.orElse(nullCompany);
        // или ВЫЗВАТЬ метод (orElseGet)
//        return res.orElseGet(this::getNotFonded);
    }

}
