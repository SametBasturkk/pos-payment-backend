package com.pospayment.pospayment.controller;

import com.pospayment.pospayment.exception.TokenException;
import com.pospayment.pospayment.model.Company;
import com.pospayment.pospayment.service.CompanyService;
import com.pospayment.pospayment.util.JwtToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private CompanyService companyService;

    private JwtToken jwtToken;

    public CompanyController(CompanyService companyService, JwtToken jwtToken) {
        this.companyService = companyService;
        this.jwtToken = jwtToken;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCompany(@RequestHeader String Authorization, @RequestBody Company company) throws TokenException {
        jwtToken.validateToken(Authorization);
        companyService.saveCompany(company);
        return ResponseEntity.ok("Company created successfully");
    }

    @GetMapping("/list")
    public ResponseEntity<String> getCompanies(){
        return ResponseEntity.ok(companyService.getCompanyList());
    }

}
