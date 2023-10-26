package ru.perm.v.companies.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.perm.v.companies.dto.CompanyDto;
import ru.perm.v.companies.service.CompanyService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/company")
public class CompanyRest {

    Logger log = LoggerFactory.getLogger(CompanyRest.class);

    private CompanyService companyService;

    public CompanyRest(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CompanyDto>> getAll() {
        log.info("get /company/getAll");
        List<CompanyDto> dtos = companyService
                .getAll()
                .stream()
                .map(c -> new CompanyDto(
                        c.getN(),
                        c.getShortname(),
                        c.getFullname(),
                        c.getInn(),
                        c.getOgrn(),
                        c.getAddressPost(),
                        c.getAddressUr(),
                        c.getDirector()
                ) {
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getById(@PathVariable Long id) {
        log.info("------------------------");
        log.info(String.format("get /company/getById/%d", id));
        try {
            return ResponseEntity.ok(companyService.getByN(id));
        } catch (Exception e) {
            String errorMessage = String.format("Company not found id=%s", id);
            log.error(errorMessage);
            return new ResponseEntity(errorMessage, HttpStatus.BAD_GATEWAY);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) {
        log.info(String.format("delete /company/deleteById/%d", id));
        try {
            companyService.deleteById(id);
            return ResponseEntity.ok(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

}
