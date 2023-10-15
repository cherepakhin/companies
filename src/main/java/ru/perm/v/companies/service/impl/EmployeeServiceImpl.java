package ru.perm.v.companies.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.perm.v.companies.entity.EmployeeEntity;
import ru.perm.v.companies.repository.EmployeeRepository;
import ru.perm.v.companies.service.EmployeeService;

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
        return employeeRepository.findAll();
    }

    @Override
    public EmployeeEntity getByN(Long n) {
        Optional<EmployeeEntity> res = employeeRepository.findById(n);
        return res.orElseGet(this::getNotFounded);
    }

    private EmployeeEntity getNotFounded() {
        return nullEmployee;
    }

}
