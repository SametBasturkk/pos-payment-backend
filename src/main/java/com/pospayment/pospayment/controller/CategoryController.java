package com.pospayment.pospayment.controller;

import com.pospayment.pospayment.exception.TokenException;
import com.pospayment.pospayment.model.Category;
import com.pospayment.pospayment.service.CategoryService;
import com.pospayment.pospayment.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private JwtToken jwtToken;

    @PostMapping("/create")
    public ResponseEntity<String> createCategory(@RequestHeader String Authorization, @RequestBody Category category) throws TokenException {
        jwtToken.validateToken(Authorization);
        categoryService.saveCategory(jwtToken.getUsername(Authorization),category);
        return ResponseEntity.ok("Category created successfully");
    }

    @GetMapping("/list")
    public ResponseEntity<String> getCategories(@RequestHeader String Authorization) throws TokenException {
        jwtToken.validateToken(Authorization);
        return ResponseEntity.ok(categoryService.getCategoryList(jwtToken.getUsername(Authorization)));
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteCategory(@RequestHeader String Authorization, @RequestParam String uuid) throws TokenException {
        jwtToken.validateToken(Authorization);
        categoryService.deleteCategory(uuid);
        return ResponseEntity.ok("Category deleted successfully");
    }
}
