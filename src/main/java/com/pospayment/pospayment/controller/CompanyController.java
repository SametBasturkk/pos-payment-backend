package com.pospayment.pospayment.controller;

import com.pospayment.pospayment.exception.TokenException;
import com.pospayment.pospayment.model.Company;
import com.pospayment.pospayment.service.CompanyService;
import com.pospayment.pospayment.util.JwtToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
@Slf4j
public class CompanyController {

    private CompanyService companyService;

    private JwtToken jwtToken;

    public CompanyController(CompanyService companyService, JwtToken jwtToken) {
        this.companyService = companyService;
        this.jwtToken = jwtToken;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCompany(@RequestHeader String Authorization, @RequestBody Company company) throws TokenException {
        companyService.saveCompany(company);
        log.info("Company created successfully : {}", company);
        return ResponseEntity.ok("Company created successfully");
    }

    @GetMapping("/list")
    public ResponseEntity<String> getCompanies(){
        log.info("Company list request");
        return ResponseEntity.ok(companyService.getCompanyList());
    }

}
