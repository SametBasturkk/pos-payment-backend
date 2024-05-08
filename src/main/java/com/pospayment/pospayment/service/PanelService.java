package com.pospayment.pospayment.service;

import com.pospayment.pospayment.model.User;
import com.pospayment.pospayment.repository.UserRepo;
import com.pospayment.pospayment.util.Converter;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class PanelService {

    private UserRepo userRepo;
    private ProductService productService;
    private CategoryService categoryService;
    private OrderService orderService;
    private Converter converter;

    public PanelService(UserRepo userRepo, ProductService productService, CategoryService categoryService, OrderService orderService, Converter converter) {
        this.userRepo = userRepo;
        this.productService = productService;
        this.categoryService = categoryService;
        this.orderService = orderService;
        this.converter = converter;
    }

    public String getOverview(String username) {
        HashMap<String, Integer> overview = new HashMap<>();
        User user = userRepo.findByUsername(username);
        overview.put("totalProducts", productService.getAllProducts(user.getCompany()).size());
        overview.put("totalCategories", categoryService.getCategoryList(username).size());
        overview.put("totalOrders", orderService.getAllOrders(user.getCompany()).size());

        return converter.convertToJson(overview);
    }
}
