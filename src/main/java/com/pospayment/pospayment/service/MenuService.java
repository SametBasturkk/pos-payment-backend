package com.pospayment.pospayment.service;

import com.pospayment.pospayment.dto.MenuDTO;
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

    public void saveMenu(Menu menu) {
        menuRepo.save(menu);
    }

    @Transactional
    public void deleteMenu(String uuid) {
        menuRepo.deleteByUUID(uuid);
    }

    public void deactiveMenu(String id) {
        Menu menu = menuRepo.findById(id).get();
        menu.setIsActive(false);
        menuRepo.save(menu);
    }


    public Menu getMenu(String id) {
        return menuRepo.findById(id).get();
    }

    public String getAllMenus(Integer companyId) {
        return jsonConverter.convertToJson(menuRepo.findByCompanyId(companyId));
    }


    public String getMenuItems(String uuid) {
        MenuDTO menuDTO = new MenuDTO();
        HashMap<String, List<Product>> menuItems = new HashMap<>();
        menuDTO.setMenuName(menuRepo.getMenuName(uuid));



        List<String> categories = menuRepo.getMenuCategories(uuid);

        for (String category : categories) {
            menuItems.put(categoryService.getCategoryName(category), productService.getProductsByCategory(category));
        }

        menuDTO.setMenuItems(menuItems);

        return jsonConverter.convertToJson(menuDTO);
    }
}
