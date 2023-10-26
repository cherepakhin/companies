package ru.perm.v.companies.util;

import ru.perm.v.companies.dto.EmployeeDto;
import ru.perm.v.companies.entity.EmployeeEntity;

public class EmployeeMapper {

    public static EmployeeEntity fromDtoToEntity(EmployeeDto dto) {
        EmployeeEntity entity = new EmployeeEntity();
        entity.setN(dto.getN());
        entity.setLastname(dto.getLastname());
        entity.setFirstname(dto.getFirstname());
        entity.setFathername(dto.getFathername());
        entity.setBirthday(Util.fromStringToDate(dto.getBirthday()));
        return entity;
    }

    public static EmployeeDto fromEntityToDto(EmployeeEntity entity) {
        EmployeeDto dto = new EmployeeDto();
        dto.setN(entity.getN());
        dto.setLastname(entity.getLastname());
        dto.setFirstname(entity.getFirstname());
        dto.setFathername(entity.getFathername());
        dto.setBirthday(Util.fromDateToString(entity.getBirthday()));
        return dto;
    }
}
