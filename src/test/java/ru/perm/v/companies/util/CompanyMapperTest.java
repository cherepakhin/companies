package ru.perm.v.companies.util;

import org.junit.jupiter.api.Test;
import ru.perm.v.companies.dto.CompanyDto;
import ru.perm.v.companies.entity.CompanyEntity;
import ru.perm.v.companies.entity.EmployeeEntity;
import ru.perm.v.companies.mappers.CompanyMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CompanyMapperTest {

    @Test
    public void testFromEntityToDto_Positive() {
        CompanyEntity entity = new CompanyEntity();
        entity.setN(1L);
        entity.setShortname("ABC");
        entity.setFullname("ABC Corporation");
        entity.setInn("1234567890");
        entity.setOgrn("0987654321");
        entity.setAddressPost("123 Main St.");
        entity.setAddressUr("456 Elm St.");
        EmployeeEntity directorEntity = new EmployeeEntity();
        directorEntity.setN(1L);
        entity.setDirector(directorEntity);

        CompanyDto dto = CompanyMapper.fromEntityToDto(entity);

        assertEquals(entity.getN(), dto.getN());
        assertEquals(entity.getShortname(), dto.getShortname());
        assertEquals(entity.getFullname(), dto.getFullname());
        assertEquals(entity.getInn(), dto.getInn());
        assertEquals(entity.getOgrn(), dto.getOgrn());
        assertEquals(entity.getAddressPost(), dto.getAddressPost());
        assertEquals(entity.getAddressUr(), dto.getAddressUr());
        assertEquals(entity.getDirector().getN(), dto.getDirector().getN());
    }

    @Test
    public void testFromEntityToDto_NullEntity() {
        CompanyEntity entity = null;

        CompanyDto dto = CompanyMapper.fromEntityToDto(entity);

        assertNull(dto);
    }
}