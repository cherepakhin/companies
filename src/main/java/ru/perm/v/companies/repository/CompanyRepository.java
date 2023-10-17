package ru.perm.v.companies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import ru.perm.v.companies.entity.CompanyEntity;

import java.util.Optional;

@Repository
public interface CompanyRepository
        extends JpaRepository<CompanyEntity, Long>, QuerydslPredicateExecutor<CompanyEntity> {
    @Override
    Optional<CompanyEntity> findById(Long n);
}
