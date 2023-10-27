package ru.perm.v.companies.util;

import org.junit.jupiter.api.Test;
import ru.perm.v.companies.dto.EmployeeDto;
import ru.perm.v.companies.entity.EmployeeEntity;
import ru.perm.v.companies.mappers.EmployeeMapper;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Generated EasyCode from Idea plugin. Prompt: Write unit tests for this code ...
public class EmployeeMapperTest {

    @Test
    public void testFromDtoToEntity() {
        // Arrange
        EmployeeDto dto = new EmployeeDto();
        dto.setN(123L);
        dto.setLastname("Doe");
        dto.setFirstname("John");
        dto.setFathername("Jane");

        dto.setBirthday("01/01/1990");

        // Act
        EmployeeEntity entity = EmployeeMapper.fromDtoToEntity(dto);

        // Assert
        assertEquals(123, entity.getN());
        assertEquals("Doe", entity.getLastname());
        assertEquals("John", entity.getFirstname());
        assertEquals("Jane", entity.getFathername());

        assertEquals(LocalDate.of(1990, 1, 1), entity.getBirthday());
    }

    @Test
    public void testFromEntityToDto() {
        // Arrange
        EmployeeEntity entity = new EmployeeEntity();
        entity.setN(456L);
        entity.setLastname("Smith");
        entity.setFirstname("Jane");
        entity.setFathername("John");
        entity.setBirthday(LocalDate.of(1990, 1, 1));

        // Act
        EmployeeDto dto = EmployeeMapper.fromEntityToDto(entity);

        // Assert
        assertEquals(456, dto.getN());
        assertEquals("Smith", dto.getLastname());
        assertEquals("Jane", dto.getFirstname());
        assertEquals("John", dto.getFathername());
        assertEquals("01/01/1990", dto.getBirthday());
    }
}
