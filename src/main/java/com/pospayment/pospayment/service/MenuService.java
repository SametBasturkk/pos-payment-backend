package com.pospayment.pospayment.service;

import com.pospayment.pospayment.dto.MenuDTO;
import com.pospayment.pospayment.dto.ProductDTO;
import com.pospayment.pospayment.model.Category;
import com.pospayment.pospayment.model.Company;
import com.pospayment.pospayment.model.Menu;
import com.pospayment.pospayment.model.Product;
import com.pospayment.pospayment.repository.MenuRepo;
import com.pospayment.pospayment.util.Converter;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MenuService {

    private MenuRepo menuRepo;

    private ProductService productService;

    private Converter converter;


    public MenuService(MenuRepo menuRepo, ProductService productService, Converter converter) {
        this.menuRepo = menuRepo;
        this.productService = productService;
        this.converter = converter;
    }


    public void saveMenu(Company company, Menu menu) {
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

    public List<MenuDTO> getAllMenus(Company company) {
        List<Menu> menus = menuRepo.findByCompany(company);
        List<MenuDTO> menuDTO = new ArrayList<>();

        for (Menu menu : menus) {
            menuDTO.add(converter.convertToDTO(menu, MenuDTO.class));
        }
        return menuDTO;
    }


    public String getMenuItems(String id) {
        MenuDTO menuDTO = new MenuDTO();
        HashMap<String, List<ProductDTO>> menuItems = new HashMap<>();
        menuDTO.setMenuName(menuRepo.findById(id).get().getName());
        menuDTO.setCompanyId(menuRepo.findById(id).get().getCompany().getId());


        List<Category> categories = menuRepo.findById(id).get().getCategories();

        for (Category category : categories) {
            List<Product> products = productService.getProductsByCategory(category);
            List<ProductDTO> productDTO = new ArrayList<>();

            for (Product product : products) {
                productDTO.add(converter.convertToDTO(product, ProductDTO.class));
            }


            menuItems.put(category.getName() , productDTO);
        }

        menuDTO.setMenuItems(menuItems);

        return converter.convertToJson(menuDTO);
    }
}
