package ru.perm.v.companies.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.perm.v.companies.entity.PeopleEntity;
import ru.perm.v.companies.repository.PeopleRepository;
import ru.perm.v.companies.service.PeopleService;

import java.util.List;
import java.util.Optional;

@Service
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private PeopleRepository peopleRepository;

    private static PeopleEntity nullPeople = new PeopleEntity(-1);

    @Override
    public List<PeopleEntity> getAll() {
        return peopleRepository.findAll();
    }

    @Override
    public PeopleEntity getByN(Long n) {
        Optional<PeopleEntity> res = peopleRepository.findById(n);
        return res.orElseGet(this::getNotFonded);
    }

    private PeopleEntity getNotFonded() {
        return nullPeople;
    }

}
