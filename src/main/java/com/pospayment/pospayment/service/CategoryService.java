package com.pospayment.pospayment.service;

import com.pospayment.pospayment.model.Category;
import com.pospayment.pospayment.repository.CategoryRepo;
import com.pospayment.pospayment.util.JsonConverter;
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
        Integer companyID = userService.getCompanyID(userName);
        category.setCompanyID(companyID);
        categoryRepo.save(category);
    }

    public String getCategoryList(String userName) {
        Integer companyID = userService.getCompanyID(userName);
        return jsonConverter.convertToJson(categoryRepo.findByCompanyID(companyID));
    }

    public void deleteCategory(String id) {
        categoryRepo.deleteById(id);
    }


}
