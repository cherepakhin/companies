package ru.perm.v.companies.dto;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidatorEmployeeDtoTest {
    @Test
    void validateFirstname() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstname("");

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<EmployeeDto>> violations = validator.validate(employeeDto);

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
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstname("FIRSTNAME");
        employeeDto.setLastname("");
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<EmployeeDto>> violations = validator.validate(employeeDto);

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
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstname("FIRSTNAME");
        employeeDto.setLastname("LASTNAME");
        employeeDto.setBirthday("");

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<EmployeeDto>> violations = validator.validate(employeeDto);

        HashMap<String, String> errors = new HashMap<>();
        violations.forEach(violation ->
                errors.put(
                        violation.getPropertyPath().toString(),
                        violation.getMessage()));

        assertEquals(1, errors.size());
        assertEquals(Set.of("birthday"), errors.keySet());
        assertEquals("No birthday", errors.get("birthday"));
    }
}