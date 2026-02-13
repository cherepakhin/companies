package ru.perm.v.companies.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import ru.perm.v.companies.entity.EmployeeEntity;

import java.util.List;

@Repository
public interface EmployeeRepository
        extends CrudRepository<EmployeeEntity, Long>, QueryByExampleExecutor<EmployeeEntity> {
    List<EmployeeEntity> findAllByOrderByNAsc();
    List<EmployeeEntity> findByLastnameOrderByFirstnameAsc(String lastName);

    List<EmployeeEntity> findByLastnameOrderByNDesc(String lastName);
    List<EmployeeEntity> findByLastnameOrderByNAsc(String lastName);
    List<EmployeeEntity> findByLastnameLikeOrderByNDesc(String lastName);
    List<EmployeeEntity> findByLastnameOrderByLastnameAsc(String lastName);

//    @Query(value = "select e from Employee e where e.job.desc = :desc")
//    Page<Employee> findByDesc(Pageable pageable, @Param("desc") String desc);

//    EmployeeEntity create(EmployeeEntity employee);
}
