package ru.perm.v.companies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.perm.v.companies.entity.EmployeeEntity;

import java.util.List;

//        extends CrudRepository<EmployeeEntity, Long>, QueryByExampleExecutor<EmployeeEntity> {

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    List<EmployeeEntity> findAllByOrderByNAsc();
    List<EmployeeEntity> findByLastnameOrderByFirstnameAsc(String lastName);


    List<EmployeeEntity> findByLastnameOrderByNDesc(String lastName);
    List<EmployeeEntity> findByLastnameOrderByNAsc(String lastName);
    List<EmployeeEntity> findByLastnameContainingOrderByNAsc(String lastName);
    List<EmployeeEntity> findByLastnameOrderByLastnameAsc(String lastName);

//    EmployeeEntity create(EmployeeEntity employee);
}
