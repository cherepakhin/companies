package ru.perm.v.companies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.perm.v.companies.entity.PeopleEntity;

@Repository
public interface PeopleRepository extends JpaRepository<PeopleEntity, Long> {
}
