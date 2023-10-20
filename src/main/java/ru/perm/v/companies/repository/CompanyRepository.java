package ru.perm.v.companies.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.perm.v.companies.entity.CompanyEntity;

// , QueryByExampleExecutor<CompanyEntity>

@Repository
public interface CompanyRepository extends CrudRepository<CompanyEntity, Long> {
//    Optional<CompanyEntity> findById(Long n);
//    List<CompanyEntity> findAll();
}
