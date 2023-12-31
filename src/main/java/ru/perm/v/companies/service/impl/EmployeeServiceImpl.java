package ru.perm.v.companies.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import ru.perm.v.companies.dto.EmployeeDto;
import ru.perm.v.companies.entity.EmployeeEntity;
import ru.perm.v.companies.repository.EmployeeRepository;
import ru.perm.v.companies.service.EmployeeService;
import ru.perm.v.companies.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl() {
        super();
    }

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this();
        this.employeeRepository = employeeRepository;
    }

    public static List<EmployeeDto> convertFromListEntity(List<EmployeeEntity> entities) {
        return entities.stream().map(e -> convertFromEntityToDto(e)).collect(Collectors.toList());
    }

    public static EmployeeEntity convertFromDtoToEntity(EmployeeDto dto) {
        return new EmployeeEntity(
                dto.getN(),
                dto.getFirstname(),
                dto.getLastname(),
                dto.getFathername(),
                Util.fromStringToDate(dto.getBirthday())
        );
    }

    public static EmployeeDto convertFromEntityToDto(EmployeeEntity entity) {
        return new EmployeeDto(
                entity.getN(),
                entity.getFirstname(),
                entity.getLastname(),
                entity.getFathername(),
                Util.fromDateToString(entity.getBirthday())
        );
    }

    @Override
    public EmployeeDto create(EmployeeDto employee) {
        EmployeeEntity entity = convertFromDtoToEntity(employee);
        entity.setN(this.getNextN());
        EmployeeEntity created = employeeRepository.save(entity);
        return convertFromEntityToDto(created);
    }

    @Override
    public EmployeeDto update(EmployeeDto employee) {
        EmployeeEntity entity = convertFromDtoToEntity(employee);
        EmployeeEntity updated = employeeRepository.save(entity);
        return convertFromEntityToDto(updated);
    }

    @Override
    public List<EmployeeDto> getAll() {
        ArrayList<EmployeeDto> dtos = new ArrayList<>();

        Iterable<EmployeeEntity> all = employeeRepository.findAll();
        all.iterator().forEachRemaining(entity -> dtos.add(this.convertFromEntityToDto(entity)));
//        List<EmployeeEntity> enities = Streamable.of(all).toList(); OK
        return dtos;
    }

    @Override
    public EmployeeDto getByN(Long n) throws Exception {
        Optional<EmployeeEntity> res = employeeRepository.findById(n);
//        employeeRepository.findById(n).ifPresent(employee -> new EmployeeDto(employee));
        if (res.isPresent()) {
            return convertFromEntityToDto(res.get());
        } else {
            throw new Exception(String.format("Employee with n=%s not found", n));
        }
    }

    @Override
    public List<EmployeeDto> getByFirstName(String name) {
        EmployeeEntity query = new EmployeeEntity();
        query.setFirstname(name);
//
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("n", "lastname", "fathername", "birthday")
                .withIncludeNullValues()
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

    public List<EmployeeDto> findByLastnameLikeOrderByN(String lastName) {
        return convertFromListEntity(employeeRepository.findByLastnameContainingOrderByNAsc(lastName));
    }

    public List<EmployeeDto> findByLastnameOrderByNAsc(String lastName) {
        return convertFromListEntity(employeeRepository.findByLastnameOrderByNAsc(lastName));
    }

    @Override
    public List<EmployeeDto> findByLastnameLikeOrderByNDesc(String lastName) {
        return convertFromListEntity(employeeRepository.findByLastnameOrderByNDesc(lastName));
    }

    @Override
    public List<EmployeeDto> findByLastnameOrderByLastnameAsc(String lastName) {
        return convertFromListEntity(employeeRepository.findByLastnameOrderByLastnameAsc(lastName));
    }

    @Override
    public List<EmployeeDto> findAllByOrderByNAsc() {
        return convertFromListEntity(employeeRepository.findAllByOrderByNAsc());
    }

    @Override
    public List<EmployeeDto> findByLastnameOrderByNDesc(String lastName) {
        EmployeeEntity query = new EmployeeEntity();
        query.setLastname(lastName);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("n", "firstname", "fathername", "birthday")
                .withIncludeNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        ArrayList<EmployeeEntity> ret = new ArrayList<>();
        Example<EmployeeEntity> example = Example.of(query, matcher);
// Выборка подробно
//        Iterable<EmployeeEntity> all = employeeRepository.findAll(example);
//        all.forEach(ret::add);
// Выборка коротко
        employeeRepository.findAll(example).forEach(ret::add);
//        employeeRepository.findAll().forEach(ret::add);
        return convertFromListEntity(ret);
    }

    @Override
    public List<EmployeeDto> findByLastnameLikeOrderByNAsc(String lastName) {
        EmployeeEntity query = new EmployeeEntity();
        query.setLastname(lastName);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("n", "firstname", "fathername", "birthday")
                .withIncludeNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        ArrayList<EmployeeEntity> ret = new ArrayList<>();
        Example<EmployeeEntity> example = Example.of(query, matcher);
        employeeRepository.findAll(example).forEach(ret::add);
//        Example<EmployeeEntity> example = Example.of(query, matcher);
//        employeeRepository.findAll(example).forEach(ret::add);
        return convertFromListEntity(ret);
    }

    @Override
    public void deleteByN(Long n) throws Exception {
        Optional<EmployeeEntity> res = employeeRepository.findById(n);
        if (res.isPresent()) {
            employeeRepository.deleteById(n);
        } else {
            throw new Exception(String.format("Employee with n=%s not found", n));
        }

    }

    @Override
    public Long getNextN() {
        return employeeRepository.getNextN();
    }

}
