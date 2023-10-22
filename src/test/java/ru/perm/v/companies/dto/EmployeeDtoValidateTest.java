package ru.perm.v.companies.dto;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeDtoValidateTest {
    @Test
    void validateFirstname() {
        EmployeeDto dto = new EmployeeDto();
        dto.setFirstname("");

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<EmployeeDto>> violations = validator.validate(dto);

        HashMap<String, String> errors = new HashMap<>();
        violations.forEach(violation ->
                errors.put(
                        violation.getPropertyPath().toString(),
                        violation.getMessage()));

        assertEquals(1, errors.size());
        assertEquals("Firstname empty", errors.get("firstname"));
    }

    @Test
    void validateLastname() {
        EmployeeDto dto = new EmployeeDto();
        dto.setFirstname("FIRSTNAME");
        dto.setLastname("");
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<EmployeeDto>> violations = validator.validate(dto);

        HashMap<String, String> errors = new HashMap<>();
        violations.forEach(violation ->
                errors.put(
                        violation.getPropertyPath().toString(),
                        violation.getMessage()));

        assertEquals(1, errors.size());
        assertEquals("Lastname empty", errors.get("lastname"));
    }

    @Test
    void validateBirthday() {
        EmployeeDto dto = new EmployeeDto();
        dto.setFirstname("FIRSTNAME");
        dto.setLastname("LASTNAME");
        dto.setBirthday("");

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<EmployeeDto>> violations = validator.validate(dto);

        HashMap<String, String> errors = new HashMap<>();
        violations.forEach(violation ->
                errors.put(
                        violation.getPropertyPath().toString(),
                        violation.getMessage()));

        assertEquals(1, errors.size());
        assertEquals("No birthday", errors.get("birthday"));
    }
}