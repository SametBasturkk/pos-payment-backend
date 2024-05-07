package com.pospayment.pospayment.service;

import com.pospayment.pospayment.exception.NotFoundException;
import com.pospayment.pospayment.model.Category;
import com.pospayment.pospayment.model.Company;
import com.pospayment.pospayment.repository.CategoryRepo;
import com.pospayment.pospayment.util.Converter;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryService {

    private CategoryRepo categoryRepo;

    private Converter converter;

    private UserService userService;

    public CategoryService(CategoryRepo categoryRepo, Converter converter, UserService userService) {
        this.categoryRepo = categoryRepo;
        this.converter = converter;
        this.userService = userService;
    }

    public void saveCategory(String userName,Category category) {
        Company company = userService.getCompany(userName);
        category.setCompany(company);
        categoryRepo.save(category);
    }

    public List<Category> getCategoryList(String userName) {
        Company company = userService.getCompany(userName);
        List<Category> category = categoryRepo.findByCompany(company);

        if (category.isEmpty()) {
            throw new NotFoundException("Category not found");
        }

        return category;

    }

    @Transactional
    public void deleteCategory(String id) {
        categoryRepo.deleteById(id);
    }



}
