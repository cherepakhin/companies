package ru.perm.v.companies.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.perm.v.companies.dto.EmployeeDto;
import ru.perm.v.companies.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")
public class EmployeeRest {

    private EmployeeService employeeService;

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

}
