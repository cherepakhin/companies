package ru.perm.v.companies.util;

import ru.perm.v.companies.dto.CompanyDto;
import ru.perm.v.companies.entity.CompanyEntity;

public class CompanyMapper {
    public static CompanyEntity fromDtoToEntity(CompanyDto dto) {
        CompanyEntity entity = new CompanyEntity();
        entity.setN(dto.getN());
        entity.setShortname(dto.getShortname());
        entity.setFullname(dto.getFullname());
        entity.setInn(dto.getInn());
        entity.setOgrn(dto.getOgrn());
        entity.setAddressPost(dto.getAddressPost());
        entity.setAddressUr(dto.getAddressUr());
        entity.setDirector(EmployeeMapper.fromDtoToEntity(dto.getDirector()));
        return entity;
    }

    public static CompanyDto fromEntityToDto(CompanyEntity entity) {
        CompanyDto dto = new CompanyDto();
        dto.setN(entity.getN());
        dto.setShortname(entity.getShortname());
        dto.setFullname(entity.getFullname());
        dto.setInn(entity.getInn());
        dto.setOgrn(entity.getOgrn());
        dto.setAddressPost(entity.getAddressPost());
        dto.setAddressUr(entity.getAddressUr());
        dto.setDirector(EmployeeMapper.fromEntityToDto(entity.getDirector()));
        return dto;
    }
}
