package ru.perm.v.companies.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import ru.perm.v.companies.entity.EmployeeEntity;

import java.util.List;

@Repository
public interface EmployeeRepository
        extends CrudRepository<EmployeeEntity, Long>, QueryByExampleExecutor<EmployeeEntity> {
    List<EmployeeEntity> findByLastnameOrderByFirstnameAsc(String lastName);


    List<EmployeeEntity> findByLastnameOrderByNDesc(String lastName);
    List<EmployeeEntity> findByLastnameOrderByNAsc(String lastName);
    List<EmployeeEntity> findByLastnameLikeOrderByNDesc(String lastName);
    List<EmployeeEntity> findByLastnameOrderByLastnameAsc(String lastName);
    EmployeeEntity create(EmployeeEntity employee);
}
