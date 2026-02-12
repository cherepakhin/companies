package ru.perm.v.companies.mapper;

import org.junit.jupiter.api.Test;
import ru.perm.v.companies.dto.CompanyDto;
import ru.perm.v.companies.entity.CompanyEntity;

import static org.junit.jupiter.api.Assertions.*;

class MapperCompanyTest {

    @Test
    void toDTO() {
        Long N = 1L;
        String SHORTNAME = "Short";
        String FULLNAME = "Full";
        String INN = "123";
        String OGRN = "OGRN";
        String ADDRESS_UR = "ur address";
        String ADDRESS_POST = "post address";

        CompanyEntity entity = new CompanyEntity();
        entity.setN(N);
        entity.setShortname(SHORTNAME);
        entity.setFullname(FULLNAME);
        entity.setInn(INN);
        entity.setOgrn(OGRN);
        entity.setAddressUr(ADDRESS_UR);
        entity.setAddressPost(ADDRESS_POST);

        CompanyDto dto = new MapperCompany().toDTO(entity);

        assertEquals(N, dto.getN());
        assertEquals(SHORTNAME, dto.getShortname());
        assertEquals(FULLNAME, dto.getFullname());
        assertEquals(INN, dto.getInn());
        assertEquals(OGRN, dto.getOgrn());
        assertEquals(ADDRESS_UR, dto.getAddressUr());
        assertEquals(ADDRESS_POST, dto.getAddressPost());
    }

    @Test
    void toEntity() {
        Long N = 1L;
        String SHORTNAME = "Short";
        String FULLNAME = "Full";
        String INN = "123";
        String OGRN = "OGRN";
        String ADDRESS_UR = "ur address";
        String ADDRESS_POST = "post address";

        CompanyDto dto = new CompanyDto();
        dto.setN(N);
        dto.setShortname(SHORTNAME);
        dto.setFullname(FULLNAME);
        dto.setInn(INN);
        dto.setOgrn(OGRN);
        dto.setAddressUr(ADDRESS_UR);
        dto.setAddressPost(ADDRESS_POST);

        CompanyEntity entity = new MapperCompany().toEntity(dto);

        assertEquals(N, entity.getN());
        assertEquals(SHORTNAME, entity.getShortname());
        assertEquals(FULLNAME, entity.getFullname());
        assertEquals(INN, entity.getInn());
        assertEquals(OGRN, entity.getOgrn());
        assertEquals(ADDRESS_UR, entity.getAddressUr());
        assertEquals(ADDRESS_POST, entity.getAddressPost());
    }
}