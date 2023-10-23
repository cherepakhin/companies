package ru.perm.v.companies.rest;

import ru.perm.v.companies.dto.EmployeeDto;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidatorEmployeeDto {
    public List<String> validate(EmployeeDto dto) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.usingContext().getValidator();
        Set<ConstraintViolation<EmployeeDto>> validates = validator.validate(dto);
        List<String> ret = new ArrayList<>();
        if (validates.size() > 0) {
            List<ConstraintViolation<EmployeeDto>> errors = validates.stream().collect(Collectors.toList());
            for (ConstraintViolation<EmployeeDto> validateErr : errors) {
                ret.add(String.format("field: %s, error: %s", validateErr.getPropertyPath(), validateErr.getMessage()));
            }
        }
        return ret;
    }
}
