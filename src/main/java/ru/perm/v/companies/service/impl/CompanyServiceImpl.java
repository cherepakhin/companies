package ru.perm.v.companies.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.perm.v.companies.dto.CompanyDto;
import ru.perm.v.companies.entity.CompanyEntity;
import ru.perm.v.companies.repository.CompanyRepository;
import ru.perm.v.companies.rest.CompanyRest;
import ru.perm.v.companies.service.CompanyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    Logger log = LoggerFactory.getLogger(CompanyRest.class);
    private static CompanyDto nullCompany = new CompanyDto(-1L);

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyServiceImpl() {
        super();
    }

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this();
        this.companyRepository = companyRepository;
    }

    @Override
    public List<CompanyDto> getAll() {
        List<CompanyEntity> companies = new ArrayList<>();
        companyRepository.findAll().forEach(companies::add);
        List<CompanyDto> dtos = companies.stream().map(entity -> new CompanyDto(
                entity.getN(),
                entity.getShortname(),
                entity.getFullname(),
                entity.getInn(),
                entity.getOgrn(),
                entity.getAddressPost(),
                entity.getAddressUr()
        )).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public CompanyDto getByN(Long id) throws Exception {
        CompanyEntity companyEntity = getEntityById(id);
        return fromEntityToDto(companyEntity);
    }

    protected CompanyEntity getEntityById(Long id) throws Exception {
        Optional<CompanyEntity> res = companyRepository.findById(id);
        if (res.isPresent()) {
            CompanyEntity companyEntity = res.get();
            return companyEntity;
        } else {
            throw new Exception(String.format("Company with id=%s NOT FOUND", id));
        }
        // или ВЫЗВАТЬ метод (orElseGet)
        // return res.orElseGet(this::getNotFonded);
    }

    public static CompanyDto fromEntityToDto(CompanyEntity companyEntity) {
        return new CompanyDto(companyEntity.getN(),
                companyEntity.getShortname(),
                companyEntity.getFullname(),
                companyEntity.getInn(),
                companyEntity.getOgrn(),
                companyEntity.getAddressPost(),
                companyEntity.getAddressUr());
    }

    @Override
    public List<CompanyDto> getByShortName(String name) {
        List<CompanyEntity> companies = companyRepository.findByShortnameOrderByNDesc(name);
        List<CompanyDto> dtos = companies.stream().map(CompanyServiceImpl::fromEntityToDto).collect(Collectors.toList());
        return dtos;
//TODO: release on Q
//        QCompanyEntity qCompany = QCompanyEntity.companyEntity;
//        List<BooleanExpression> predicates = new ArrayList<>();
//        if (!name.isEmpty()) {
//            predicates.add(qCompany.shortname.containsIgnoreCase(name));
//        }
//        BooleanExpression expression = predicates.stream().reduce((predicate, accum) -> accum.and(predicate)).orElse(null);
//        ArrayList<CompanyEntity> companies = new ArrayList<CompanyEntity>();
//        companyRepository.findAll(expression).forEach(companies::add);
//        List<CompanyDto> dtos = companies.stream().map(CompanyServiceImpl::fromEntityToDto)
//                .collect(Collectors.toList());
//        return dtos;
    }

    @Override
    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }

// Разные способы получения результата отбора
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
