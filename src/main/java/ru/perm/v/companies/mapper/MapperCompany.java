package ru.perm.v.companies.mapper;

import ru.perm.v.companies.dto.CompanyDto;
import ru.perm.v.companies.entity.CompanyEntity;

public class MapperCompany implements IMapper<CompanyDto, CompanyEntity> {

    @Override
    public CompanyDto toDTO(CompanyEntity companyEntity) {
        CompanyDto dto = new CompanyDto();
        dto.setN(companyEntity.getN());
        dto.setShortname(companyEntity.getShortname());
        dto.setFullname(companyEntity.getFullname());
        dto.setInn(companyEntity.getInn());
        dto.setOgrn(companyEntity.getOgrn());
        dto.setAddressUr(companyEntity.getAddressUr());
        dto.setAddressPost(companyEntity.getAddressPost());
        return dto;

    }

    @Override
    public CompanyEntity toEntity(CompanyDto companyDto) {
        CompanyEntity entity = new CompanyEntity();
        entity.setN(companyDto.getN());
        entity.setShortname(companyDto.getShortname());
        entity.setFullname(companyDto.getFullname());
        entity.setInn(companyDto.getInn());
        entity.setOgrn(companyDto.getOgrn());
        entity.setAddressUr(companyDto.getAddressUr());
        entity.setAddressPost(companyDto.getAddressPost());
        return entity;
    }
}