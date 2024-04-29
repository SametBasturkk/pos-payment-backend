package com.pospayment.pospayment.service;

import com.pospayment.pospayment.dto.MenuDTO;
import com.pospayment.pospayment.model.Category;
import com.pospayment.pospayment.model.Company;
import com.pospayment.pospayment.model.Menu;
import com.pospayment.pospayment.model.Product;
import com.pospayment.pospayment.repository.MenuRepo;
import com.pospayment.pospayment.util.JsonConverter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepo menuRepo;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private JsonConverter jsonConverter;

    @Autowired
    private UserService userService;


    public void saveMenu(Company company,Menu menu) {
        menu.setCompany(company);
        menuRepo.save(menu);
    }

    @Transactional
    public void deleteMenu(String id) {
        menuRepo.deleteById(id);
    }

    public void deactiveMenu(String id) {
        Menu menu = menuRepo.findById(id).get();
        menu.setIsActive(false);
        menuRepo.save(menu);
    }


    public Menu getMenu(String id) {
        return menuRepo.findById(id).get();
    }

    public String getAllMenus(Company company) {
        return jsonConverter.convertToJson(menuRepo.findByCompany(company));
    }


    public String getMenuItems(String id) {
        MenuDTO menuDTO = new MenuDTO();
        HashMap<String, List<Product>> menuItems = new HashMap<>();
        menuDTO.setMenuName(menuRepo.findById(id).get().getName());
        menuDTO.setCompany(menuRepo.findById(id).get().getCompany());


        List<Category> categories = menuRepo.findById(id).get().getCategories();

        for (Category category : categories) {
            menuItems.put(category.getName(), productService.getProductsByCategory(category));
        }

        menuDTO.setMenuItems(menuItems);

        return jsonConverter.convertToJson(menuDTO);
    }
}
