package com.pospayment.pospayment.controller;

import com.pospayment.pospayment.dto.CategoryDTO;
import com.pospayment.pospayment.exception.TokenException;
import com.pospayment.pospayment.model.Category;
import com.pospayment.pospayment.service.CategoryService;
import com.pospayment.pospayment.util.Converter;
import com.pospayment.pospayment.util.JwtToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

    private JwtToken jwtToken;

    private Converter converter;


    public CategoryController(CategoryService categoryService, JwtToken jwtToken, Converter converter) {
        this.categoryService = categoryService;
        this.jwtToken = jwtToken;
        this.converter = converter;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCategory(@RequestHeader String Authorization, @RequestBody Category category) throws TokenException {
        jwtToken.validateToken(Authorization);
        categoryService.saveCategory(jwtToken.getUsername(Authorization),category);
        return ResponseEntity.ok("Category created successfully");
    }

    @GetMapping("/list")
    public ResponseEntity<List<CategoryDTO>> getCategories(@RequestHeader String Authorization) throws TokenException {

        jwtToken.validateToken(Authorization);
        List<CategoryDTO> categoryDTO = new ArrayList<>();

        List<Category> category = categoryService.getCategoryList(jwtToken.getUsername(Authorization));

        for (Category c : category) {
            categoryDTO.add(converter.convertToDTO(c, CategoryDTO.class));
        }

        return ResponseEntity.ok(categoryDTO);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteCategory(@RequestHeader String Authorization, @RequestParam String id) throws TokenException {
        jwtToken.validateToken(Authorization);
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted successfully");
    }
}
