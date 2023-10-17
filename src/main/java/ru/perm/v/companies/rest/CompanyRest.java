package ru.perm.v.companies.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.perm.v.companies.dto.CompanyDto;
import ru.perm.v.companies.service.CompanyService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/company")
public class CompanyRest {

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
                        c.getAddressUr()
                ) {
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        log.info(String.format("get /company/getById/%d", id));
        try {
            return ResponseEntity.ok(companyService.getByN(id));
        } catch (Exception e) {
            return new ResponseEntity(String.format("Company not found id=%s", id), HttpStatus.BAD_GATEWAY);
        }
    }

}
