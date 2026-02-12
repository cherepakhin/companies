package ru.perm.v.companies.mapper;

import ru.perm.v.companies.dto.EmployeeDto;
import ru.perm.v.companies.entity.CompanyEntity;
import ru.perm.v.companies.entity.EmployeeEntity;
import ru.perm.v.companies.util.Util;

public class EmployeeMapper implements IMapper<EmployeeDto, EmployeeEntity> {
    @Override
    public EmployeeDto toDTO(EmployeeEntity employeeEntity) {
        EmployeeDto employeeDto = new EmployeeDto();
        if (employeeEntity != null) {
            employeeDto.setN(employeeEntity.getN());
            employeeDto.setFirstname(employeeEntity.getFirstname());
            employeeDto.setLastname(employeeEntity.getLastname());
            employeeDto.setFathername(employeeEntity.getFathername());
            employeeDto.setBirthday(Util.fromDateToString(employeeEntity.getBirthday()));
            if (employeeEntity.getCompanyEntity() != null) {
                employeeDto.setCompanyN(employeeEntity.getCompanyEntity().getN());
            }
        }
        return employeeDto;
    }

    @Override
    public EmployeeEntity toEntity(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        if (employeeDto != null) {
            employeeEntity.setN(employeeDto.getN());
            if (employeeDto.getCompanyN() != null) {
                CompanyEntity companyEntity = new CompanyEntity();
                companyEntity.setN(employeeDto.getCompanyN());
                employeeEntity.setCompanyEntity(companyEntity);
            }
            employeeEntity.setFirstname(employeeDto.getFirstname());
            employeeEntity.setLastname(employeeDto.getLastname());
            employeeEntity.setFathername(employeeDto.getFathername());
            if (employeeDto.getBirthday() != null) {
                employeeEntity.setBirthday(Util.fromStringToDate(employeeDto.getBirthday()));
            }
            if (employeeDto.getCompanyN() != null) {
                CompanyEntity companyEntity = new CompanyEntity();
                companyEntity.setN(employeeDto.getCompanyN());
                employeeEntity.setCompanyEntity(companyEntity);
            }
        }
        return employeeEntity;
    }
}
