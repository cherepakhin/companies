package ru.perm.v.companies.mapper;

public interface IMapper<DTO, ENTITY> {
    DTO toDTO(ENTITY entity);
    ENTITY toEntity(DTO dto);
}
