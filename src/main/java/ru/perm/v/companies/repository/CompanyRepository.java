package ru.perm.v.companies.repository;

import org.springframework.data.repository.CrudRepository;
import ru.perm.v.companies.entity.CompanyEntity;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends CrudRepository<CompanyEntity, Long> {
    Optional<CompanyEntity> findById(Long n);
    List<CompanyEntity> findAll();
}
