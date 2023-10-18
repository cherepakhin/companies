package ru.perm.v.companies.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import ru.perm.v.companies.entity.EmployeeEntity;
import ru.perm.v.companies.repository.EmployeeRepository;
import ru.perm.v.companies.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static EmployeeEntity nullEmployee = new EmployeeEntity(-1);
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(@Autowired EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeEntity> getAll() {
        List<EmployeeEntity> ret = new ArrayList<>();
        employeeRepository.findAll().forEach(ret::add);
        return ret;
    }

    @Override
    public EmployeeEntity getByN(Long n) {
        Optional<EmployeeEntity> res = employeeRepository.findById(n);
        return res.orElseGet(this::getNotFounded);
    }

    @Override
    public List<EmployeeEntity> getByFirstName(String name) {
        EmployeeEntity query = new EmployeeEntity();
        query.setFirstname(name);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("n", "lastname", "fathername", "birthday")
                .withIncludeNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        ArrayList<EmployeeEntity> ret = new ArrayList<EmployeeEntity>();
        Example<EmployeeEntity> example = Example.of(query, matcher);
// Выборка подробно
//        Iterable<EmployeeEntity> all = employeeRepository.findAll(example);
//        all.forEach(ret::add);
// Выборка коротко
        employeeRepository.findAll(example).forEach(ret::add);
        return ret;
    }


    public List<EmployeeEntity> findByLastnameOrderByNAsc(String lastName) {
        return employeeRepository.findByLastnameOrderByNAsc(lastName);
    }

    @Override
    public List<EmployeeEntity> findByLastnameLikeOrderByNDesc(String lastName) {
        return null;
    }

    @Override
    public List<EmployeeEntity> findByLastnameOrderByLastnameAsc(String lastName) {
        return employeeRepository.findByLastnameOrderByLastnameAsc(lastName);
    }

    @Override
    public List<EmployeeEntity> findByLastnameOrderByNDesc(String lastName) {
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
        return ret;
    }

    @Override
    public List<EmployeeEntity> findByLastnameLikeOrderByN(String lastName) {
        EmployeeEntity query = new EmployeeEntity();
        query.setLastname(lastName);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("n", "firstname", "fathername", "birthday")
                .withIncludeNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        ArrayList<EmployeeEntity> ret = new ArrayList<EmployeeEntity>();
        Example<EmployeeEntity> example = Example.of(query, matcher);
        employeeRepository.findAll(example).forEach(ret::add);
        return ret;
    }

//    @Override
//    public List<EmployeeEntity> findByLastnameLikeOrderByN(String lastName) {
//        return employeeRepository.findByLastnameOrderByFirstnameAsc(lastName);
//    }
//
    private EmployeeEntity getNotFounded() {
        return nullEmployee;
    }

}
