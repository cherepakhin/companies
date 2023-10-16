package ru.perm.v.companies.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.perm.v.companies.dto.CompanyDto;
import ru.perm.v.companies.entity.CompanyEntity;
import ru.perm.v.companies.entity.QCompanyEntity;
import ru.perm.v.companies.repository.CompanyRepository;
import ru.perm.v.companies.service.CompanyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CompanyServiceImpl implements CompanyService {

    private static CompanyEntity nullCompany = new CompanyEntity(-1);
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(@Autowired CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<CompanyDto> getAll() {
        List<CompanyEntity> companies = companyRepository.findAll();
        for (CompanyEntity c : companies) {
            System.out.println(c.getN());
        }
        List<CompanyDto> dtos = companies.stream().map(entity -> new CompanyDto(
                entity.getN(),
                entity.getShortname(),
                entity.getFullname(),
                entity.getInn(),
                entity.getOgrn(),
                entity.getAddressPost(),
                entity.getAddressUr()
        )).collect(Collectors.toList());
        System.out.println(dtos);
        return dtos;
    }

    private void print(List<CompanyEntity> compamies) {
        System.out.println("PRINT-----------------------------------------------------");
        for (CompanyEntity c : compamies) {
            System.out.println(c);
        }
        System.out.println("END PRINT-----------------------------------------------------");
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

    @Override
    public List<CompanyEntity> getByShortName(String name) {
        QCompanyEntity qCompany = QCompanyEntity.companyEntity;
        List<BooleanExpression> predicates = new ArrayList<>();
        if(!name.isEmpty()) {
            predicates.add(qCompany.shortname.containsIgnoreCase(name));
        }
        BooleanExpression expression = predicates.stream().reduce((predicate,accum) -> accum.and(predicate)).orElse(null);
        Iterable<CompanyEntity> iterCompany = companyRepository.findAll(expression);

        List<CompanyEntity> entities = StreamSupport.stream(iterCompany.spliterator(), false).collect(Collectors.toList());
        if(entities==null) {
            entities= new ArrayList<CompanyEntity>();
        }
        return entities;
    }

//    @Override
//    public List<CompanyEntity> getByShortName(String name) {
//        QCompanyEntity company = QCompanyEntity.companyEntity;
//        Predicate predicate = company.shortname.eq(name);
//        Iterable<CompanyEntity> companies = companyRepository.findAll(predicate);
//        ArrayList<CompanyEntity> ret = new ArrayList<>();
//        for(CompanyEntity c : companies) {
//            ret.add(c);
//        }
//
////        List<Long> ids = new ArrayList<>();
////        companies.forEach(companyEntity -> ids.add(companyEntity.getN()));
//
////      Другой способ маппинга по итератору
////        List<Long> ids = StreamSupport.stream(companies.spliterator(), false)
////                .map(CompanyEntity::getN)
////                .collect(Collectors.toList());
//        return ret;
//    }

}
