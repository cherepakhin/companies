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
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<EmployeeDto>> violations = validator.validate(dto);

        HashMap<String, String> errors = new HashMap<>();
        violations.forEach(violation ->
                errors.put(
                        violation.getPropertyPath().toString(),
                        violation.getMessage()));

        assertEquals("Firstname empty", errors.get("firstname"));
    }

    @Test
    void validateLastname() {
        EmployeeDto dto = new EmployeeDto();
        dto.setFirstname("FIRSTNAME");
        dto.setLastname("");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<EmployeeDto>> violations = validator.validate(dto);

        HashMap<String, String> errors = new HashMap<>();
        violations.forEach(violation ->
                errors.put(
                        violation.getPropertyPath().toString(),
                        violation.getMessage()));
        assertEquals("Lastname empty", errors.get("lastname"));
    }

    @Test
    void validateBirthday() {
        EmployeeDto dto = new EmployeeDto();
        dto.setFirstname("FIRSTNAME");
        dto.setLastname("LASTNAME");
        dto.setBirthday("");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

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