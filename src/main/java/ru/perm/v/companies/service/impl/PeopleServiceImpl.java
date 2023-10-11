package ru.perm.v.companies.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.perm.v.companies.entity.PeopleEntity;
import ru.perm.v.companies.repository.PeopleRepository;
import ru.perm.v.companies.service.PeopleService;

import java.util.List;

@Service
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private PeopleRepository peopleRepository;

    @Override
    public List<PeopleEntity> getAll() {
        return peopleRepository.findAll();
    }

    @Override
    public PeopleEntity getByN(Long n) {
        return peopleRepository.getById(n);
    }
}
