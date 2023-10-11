package ru.perm.v.companies.service;

import ru.perm.v.companies.entity.PeopleEntity;

import java.util.List;

public interface PeopleService {
    List<PeopleEntity> getAll();

    PeopleEntity getByN(Long n);
}
