package ru.perm.v.companies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.perm.v.companies.entity.CompanyEntity;

// , QueryByExampleExecutor<CompanyEntity>

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
//    Optional<CompanyEntity> findById(Long n);
//    List<CompanyEntity> findAll();
}
