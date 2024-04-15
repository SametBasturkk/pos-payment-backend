package com.pospayment.pospayment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pospayment.pospayment.exception.TokenException;
import com.pospayment.pospayment.model.Category;
import com.pospayment.pospayment.model.Company;
import com.pospayment.pospayment.model.User;
import com.pospayment.pospayment.service.CategoryService;
import com.pospayment.pospayment.service.CompanyService;
import com.pospayment.pospayment.service.UserService;
import com.pospayment.pospayment.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private JwtToken jwtToken;


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User userRequest) {
        userService.saveUser(userRequest);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User userRequest) {
        if (userService.loginUser(userRequest)) {
            String token = jwtToken.createToken(userRequest.getUsername());
            return ResponseEntity.ok().header("Authorization", token).body("Login successful");
        } else {
            return ResponseEntity.status(401).body("Login failed");
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestHeader String Authorization, @RequestParam String newPass, @RequestParam String oldPass) throws TokenException {
        jwtToken.validateToken(Authorization);
        userService.resetPassword(Authorization, newPass, oldPass);
        return ResponseEntity.ok("Password reset successfully");
    }

    @PostMapping("/create-company")
    public ResponseEntity<String> createCompany(@RequestHeader String Authorization, @RequestBody Company company) throws TokenException {
        jwtToken.validateToken(Authorization);
        companyService.saveCompany(company);
        return ResponseEntity.ok("Company created successfully");
    }

    @GetMapping("/companies")
    public ResponseEntity<String> getCompanies(@RequestHeader String Authorization) throws JsonProcessingException, TokenException {
        jwtToken.validateToken(Authorization);
        return ResponseEntity.ok(companyService.getCompanyList());
    }


    @PostMapping("/create-category")
    public ResponseEntity<String> createCategory(@RequestHeader String Authorization, @RequestBody Category category) throws TokenException {
        jwtToken.validateToken(Authorization);
        categoryService.saveCategory(category);
        return ResponseEntity.ok("Category created successfully");
    }

    @GetMapping("/categories")
    public ResponseEntity<String> getCategories(@RequestHeader String Authorization) throws TokenException {
        jwtToken.validateToken(Authorization);
        return ResponseEntity.ok(categoryService.getCategoryList(jwtToken.getUsername(Authorization)));
    }

    @DeleteMapping("/delete-category")
    public ResponseEntity<String> deleteCategory(@RequestHeader String Authorization, @RequestParam String id) throws TokenException {
        jwtToken.validateToken(Authorization);
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted successfully");
    }

}
