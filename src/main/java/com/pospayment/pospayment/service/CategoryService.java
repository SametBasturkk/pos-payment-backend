package com.pospayment.pospayment.service;

import com.pospayment.pospayment.model.Category;
import com.pospayment.pospayment.model.Company;
import com.pospayment.pospayment.repository.CategoryRepo;
import com.pospayment.pospayment.util.JsonConverter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private JsonConverter jsonConverter;

    @Autowired
    UserService userService;

    public void saveCategory(String userName,Category category) {
        Company company = userService.getCompany(userName);
        category.setCompany(company);
        categoryRepo.save(category);
    }

    public String getCategoryList(String userName) {
        Company company = userService.getCompany(userName);
        return jsonConverter.convertToJson(categoryRepo.findByCompany(company));
    }

    @Transactional
    public void deleteCategory(String id) {
        categoryRepo.deleteById(id);
    }



}
