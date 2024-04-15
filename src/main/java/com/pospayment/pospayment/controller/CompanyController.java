package com.pospayment.pospayment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pospayment.pospayment.exception.TokenException;
import com.pospayment.pospayment.model.Company;
import com.pospayment.pospayment.service.CompanyService;
import com.pospayment.pospayment.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private JwtToken jwtToken;

    @PostMapping("/create")
    public ResponseEntity<String> createCompany(@RequestHeader String Authorization, @RequestBody Company company) throws TokenException {
        jwtToken.validateToken(Authorization);
        companyService.saveCompany(company);
        return ResponseEntity.ok("Company created successfully");
    }

    @GetMapping("/list")
    public ResponseEntity<String> getCompanies(@RequestHeader String Authorization) throws TokenException, JsonProcessingException {
        jwtToken.validateToken(Authorization);
        return ResponseEntity.ok(companyService.getCompanyList());
    }
}
