package ru.perm.v.companies.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import ru.perm.v.companies.dto.CompanyDto;
import ru.perm.v.companies.dto.EmployeeDto;
import ru.perm.v.companies.entity.CompanyEntity;
import ru.perm.v.companies.entity.EmployeeEntity;
import ru.perm.v.companies.mapper.MapperCompany;
import ru.perm.v.companies.repository.EmployeeRepository;
import ru.perm.v.companies.service.CompanyService;
import ru.perm.v.companies.service.EmployeeService;
import ru.perm.v.companies.util.Util;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final EmployeeEntity nullEmployee = new EmployeeEntity(-1);
    private static final EmployeeDto nullEmployeeDto = new EmployeeDto(-1L, "", "", "", "",-1L);
    private EmployeeRepository employeeRepository;
    private static CompanyService companyService;

    public EmployeeServiceImpl(@Autowired EmployeeRepository employeeRepository, CompanyService companyService) {
        this.employeeRepository = employeeRepository;
        this.companyService = companyService;
    }

    @Override
    public EmployeeDto create(EmployeeDto employee) throws Exception {
        //TODO validate employee
        EmployeeEntity entity = convertFromDtoToEntity(employee);
        EmployeeEntity created = employeeRepository.save(entity);
        return convertFromEntityToDto(created);
    }

    @Override
    public List<EmployeeDto> getAll() {
        ArrayList<EmployeeDto> dtos = new ArrayList<>();

//        Iterable<EmployeeEntity> all = employeeRepository.findAll();
        employeeRepository.findAllByOrderByNAsc().forEach(employeeEntity -> dtos.add(this.convertFromEntityToDto(employeeEntity)));
//        all.iterator().forEachRemaining(entity -> dtos.add(this.convertFromEntityToDto(entity)));
//        List<EmployeeEntity> enities = Streamable.of(all).toList(); OK
        return dtos;
    }

    @Override
    public EmployeeDto getByN(Long n) {
        Optional<EmployeeEntity> res = employeeRepository.findById(n);
//        employeeRepository.findById(n).ifPresent(employee -> new EmployeeDto(employee));
        if (res.isPresent()) {
            return convertFromEntityToDto(res.get());
        }
        return nullEmployeeDto;
    }

    @Override
    public List<EmployeeDto> getByFirstName(String name) {
        EmployeeEntity query = new EmployeeEntity();
        query.setFirstname(name);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("n", "lastname", "fathername", "birthday")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        ArrayList<EmployeeEntity> entities = new ArrayList<EmployeeEntity>();
        Example<EmployeeEntity> example = Example.of(query, matcher);
// Выборка подробно
//        Iterable<EmployeeEntity> all = employeeRepository.findAll(example);
//        all.forEach(ret::add);
// Выборка коротко
        employeeRepository.findAll(example).forEach(entities::add);
        return convertFromListEntity(entities);
    }


    public List<EmployeeDto> findByLastnameOrderByNAsc(String lastName) {
        EmployeeEntity query = new EmployeeEntity();
        query.setLastname(lastName);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("n", "firstname", "fathername", "birthday", "company_n")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        ArrayList<EmployeeEntity> entities = new ArrayList<EmployeeEntity>();
        Example<EmployeeEntity> example = Example.of(query, matcher);
        employeeRepository.findAll(example).forEach(entities::add);

        return convertFromListEntity(entities);
    }

    @Override
    public List<EmployeeDto> findByLastnameLikeOrderByNDesc(String lastName) {
        return convertFromListEntity(employeeRepository.findByLastnameLikeOrderByNDesc(lastName));
    }

    @Override
    public List<EmployeeDto> findByLastnameOrderByLastnameAsc(String lastName) {
        return convertFromListEntity(employeeRepository.findByLastnameOrderByLastnameAsc(lastName));
    }


    @Override
    public List<EmployeeDto> findByLastnameOrderByNDesc(String lastName) {
        EmployeeEntity query = new EmployeeEntity();
        query.setLastname(lastName);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("n", "firstname", "fathername", "birthday")
                .withIncludeNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        ArrayList<EmployeeEntity> ret = new ArrayList<EmployeeEntity>();
        Example<EmployeeEntity> example = Example.of(query, matcher);
// Выборка подробно
//        Iterable<EmployeeEntity> all = employeeRepository.findAll(example);
//        all.forEach(ret::add);
// Выборка коротко
        employeeRepository.findAll(example).forEach(ret::add);
        return convertFromListEntity(ret);
    }

    @Override
    public List<EmployeeDto> findByLastnameLikeOrderByN(String lastName) {
        EmployeeEntity query = new EmployeeEntity();
        query.setLastname(lastName);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("n", "firstname", "fathername", "birthday", "company_n")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        ArrayList<EmployeeEntity> ret = new ArrayList<EmployeeEntity>();
        Example<EmployeeEntity> example = Example.of(query, matcher);
        employeeRepository.findAll(example).forEach(ret::add);
        return convertFromListEntity(ret);
    }

    public static List<EmployeeDto> convertFromListEntity(List<EmployeeEntity> entities) {
        return entities.stream().map(e -> convertFromEntityToDto(e)).collect(Collectors.toList());
    }

    public static EmployeeEntity convertFromDtoToEntity(EmployeeDto dto) throws Exception {
        CompanyDto companyDto = companyService.getByN(dto.getCompanyN());
        MapperCompany mapper = new MapperCompany();
        CompanyEntity companyEntity = mapper.toEntity(companyDto);
//        CompanyEntity companyEntity = ;
        return new EmployeeEntity(
                dto.getN(),
                dto.getFirstname(),
                dto.getLastname(),
                dto.getFathername(),
                Util.fromStringToDate(dto.getBirthday()),
                companyEntity
        );
    }

    public static EmployeeDto convertFromEntityToDto(EmployeeEntity entity) {
        return new EmployeeDto(
                entity.getN(),
                entity.getFirstname(),
                entity.getLastname(),
                entity.getFathername(),
                Util.fromDateToString(entity.getBirthday()),
                entity.getCompanyEntity().getN()
        );
    }

}
