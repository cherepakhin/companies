package ru.perm.v.companies.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import ru.perm.v.companies.entity.EmployeeEntity;

@Repository
public interface EmployeeRepository
        extends CrudRepository<EmployeeEntity, Long>, QueryByExampleExecutor<EmployeeEntity> {
}
