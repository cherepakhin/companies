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

    Logger log = LoggerFactory.getLogger(EmployeeRest.class);
    private EmployeeService employeeService;
    private ValidatorEmployeeDto validatorEmployeeDto = new ValidatorEmployeeDto();

    public EmployeeRest(@Autowired EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(Long id) {
        log.info(String.format("get /employee/getById/%d", id));
        try {
            return ResponseEntity.ok(employeeService.getByN(id));
        } catch (Exception e) {
            String errorMessage = String.format("Employee not found id=%s", id);
            log.error(errorMessage);
            // можно так:
            // return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(errorMessage);
            // но нужно убирать <EmployeeDto> из ответа
            return new ResponseEntity(errorMessage, HttpStatus.BAD_GATEWAY);
        }
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

    @PutMapping(value = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<EmployeeDto> create(@RequestBody EmployeeDto dto) {
        List<String> errors = validatorEmployeeDto.validate(dto);
        if (errors.size() > 0) {
            ApiError apiError =
                    new ApiError(HttpStatus.BAD_REQUEST, String.format("Errors in input dto: %s", dto), errors);
            return new ResponseEntity(
                    apiError, new HttpHeaders(), apiError.getStatus());
        }

        EmployeeDto createdDto = employeeService.create(dto);
        return ResponseEntity.ok(createdDto);
    }

    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<EmployeeDto> update(@RequestBody EmployeeDto dto) {
        if (dto.getN() == null) {
            ApiError apiError =
                    new ApiError(HttpStatus.BAD_REQUEST, "Identificator N is null. For create use PUT method", "");
            return new ResponseEntity(
                    apiError, new HttpHeaders(), apiError.getStatus());
        }
        List<String> errors = validatorEmployeeDto.validate(dto);
        if (errors.size() > 0) {
            ApiError apiError =
                    new ApiError(HttpStatus.BAD_REQUEST, String.format("Errors in input dto: %s", dto), errors);
            return new ResponseEntity(
                    apiError, new HttpHeaders(), apiError.getStatus());
        }

        EmployeeDto savedDto = employeeService.update(dto);
        return ResponseEntity.ok(savedDto);
    }

}
