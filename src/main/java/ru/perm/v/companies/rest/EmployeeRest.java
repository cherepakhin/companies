package ru.perm.v.companies.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.perm.v.companies.dto.EmployeeDto;
import ru.perm.v.companies.service.EmployeeService;
import ru.perm.v.companies.util.Util;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/employee")
public class EmployeeRest {
    private EmployeeService employeeService;

    public EmployeeRest(@Autowired EmployeeService employeeService) {
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
                        e.getBirthday(),
                        e.getCompanyN()
                ) {
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}
