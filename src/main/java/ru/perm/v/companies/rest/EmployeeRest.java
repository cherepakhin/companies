package ru.perm.v.companies.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.perm.v.companies.dto.EmployeeDto;
import ru.perm.v.companies.rest.validate.ApiError;
import ru.perm.v.companies.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

//TODO: modify, delete
@RestController
@RequestMapping("/employee")
public class EmployeeRest {

    private EmployeeService employeeService;
    private ValidatorEmployeeDto validator = new ValidatorEmployeeDto();

    Logger log = LoggerFactory.getLogger(EmployeeRest.class);

    public EmployeeRest(@Autowired EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public ResponseEntity<List<EmployeeDto>> getAll() {
        log.info("get /company/getAll");
        List<EmployeeDto> dtos = employeeService
                .getAll()
                .stream()
                .map(e -> new EmployeeDto(
                        e.getN(),
                        e.getFirstname(),
                        e.getLastname(),
                        e.getFathername(),
                        e.getBirthday()
                ) {
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // for demo validator
    @PostMapping(value = "/validate", consumes = "application/json", produces = "application/json")
    public ResponseEntity<EmployeeDto> validate(@RequestBody EmployeeDto dto) {
        List<String> errors = validator.validate(dto);
        if (errors.size() > 0) {
            ApiError apiError =
                    new ApiError(HttpStatus.BAD_REQUEST, String.format("Errors in input dto: %s", dto), errors);
            return new ResponseEntity(
                    apiError, new HttpHeaders(), apiError.getStatus());
        }
        return ResponseEntity.ok(dto);
    }
}
